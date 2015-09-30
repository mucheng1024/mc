package cn.charlie.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用LinkedHashMap实现带LRU算法的缓存池
 * 
 * 缓存池参数有：初始化容量，最大容量，加载因子，过期时间
 * 使用单例模式创建缓存池对象，设定一个过期时间，内部设置一个定时器线程用来清除过期对象
 * 当缓存池容量小于最大容量时，一旦容量达到加载因子，可以进行扩容；
 * 当缓存池容量超过最大容量时，缓存池将清除最少被访问的对象，即顶端元素；
 * 每当一个缓存对象被访问时，就将其创建时间重置为当前访问时间，目的在于重置其存活时间，从而减小被定时器清除的概率
 * @author charlie
 */
public class CachePool {
	private static CachePool instance; //缓存池唯一实例
	private Map<Object,Object> map; //缓存Map
	private int maxCapacity; //缓存的最大容量
	private long cacheTimeout; //缓存对象的过期时间
	
	private CachePool(int maxCapacity,long cacheTimeout){
		map = new LinkedHashMap<Object, Object>(16,0.75f,true);
		this.maxCapacity = maxCapacity;
		this.cacheTimeout = cacheTimeout;
		new Thread(new ClearThread()).start();
	}
	
	private CachePool(int initialCapacity, int maxCapacity, float loadFactor, boolean accessOrder,long cacheTimeout){
		map = new LinkedHashMap<Object, Object>(initialCapacity, loadFactor, accessOrder);
		this.maxCapacity = maxCapacity;
		this.cacheTimeout = cacheTimeout;
		new Thread(new ClearThread()).start();
	}
	
	/**
	 * 得到唯一实例
	 * @return
	 */
	public static CachePool getInstance(int maxCapacity,long cacheTimeout){
		if(instance == null){
			synchronized (CachePool.class) {
				if(instance == null){
					instance = new CachePool(maxCapacity,cacheTimeout);
				}
			}
		}
		return instance;
	}
	
	/**
	 * 得到唯一实例
	 * @param initialCapacity
	 * @param maxCapacity
	 * @param loadFactor
	 * @param accessOrder
	 * @return
	 */
	public static CachePool getInstance(int initialCapacity, int maxCapacity, float loadFactor, boolean accessOrder,long cacheTimeout){
		if(instance == null){
			synchronized (CachePool.class) {
				if(instance == null){
					instance = new CachePool(initialCapacity, maxCapacity, loadFactor, accessOrder,cacheTimeout);
				}
			}
		}
		return instance;
	}
	
	/**
	 * 清除所有缓存实体
	 */
	public synchronized void clearAllItems(){
		map.clear();
	}
	
	/**
	 * 打印所有的缓存实体
	 */
	public synchronized void printAllItems(){
		System.out.println(map);
	}
	
	/**
	 * 获取缓存实体，注意要重置创建时间
	 * @param name
	 * @return
	 */
	public synchronized Object getCacheItem(Object name){
		if(!map.containsKey(name)){
			return null;
		}
		CacheItem cacheItem = (CacheItem) map.get(name);
		cacheItem.setCreateTime(); //重置创建时间
		return cacheItem;
	}
	
	/**
	 * 存放缓存实体，注意判断缓存池容量是否达到上限
	 * @param name
	 * @param obj
	 */
	public synchronized void putCacheItem(Object name,Object obj){
		if(!map.containsKey(name)){
			while(map.size() >= maxCapacity){
				System.out.print("缓存池已满，删除对象为：");
				Object[] keys = map.keySet().toArray();
				System.out.println(keys[0]+"="+map.get(keys[0]));
				map.remove(keys[0]);
			}
			map.put(name, new CacheItem(obj));
		}
		//如果key已存在，则重置缓存实体和创建时间
		CacheItem cacheItem = (CacheItem) map.get(name);
		cacheItem.setCreateTime();
		cacheItem.setEntity(obj);	
	}
	
	/**
	 * 移除缓存数据
	 * @param name
	 */
	public synchronized void removeCacheItem(Object name){
		if(!map.containsKey(name)){
			return;
		}
		map.remove(name);
	}
	
	/**
	 * 获取缓存数据的数量
	 * @return
	 */
	public synchronized int getSize(){
		return map.size();
	}
	
	private class ClearThread implements Runnable{
		ClearThread() {
			Thread.currentThread().setName("clear cache thread");
		}

		public void run() {
			while(true){
				try{
					Object[] keys = map.keySet().toArray();
					for(Object key : keys){
						CacheItem entity = (CacheItem) map.get(key);						
						if(entity.isExpired(cacheTimeout)){
							synchronized (map) {
								map.remove(key);
							}
						}
					}
					System.out.println("清除过期对象后缓存池中剩余对象有：\n"+map);
					Thread.sleep(cacheTimeout);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
