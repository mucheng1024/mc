package cn.charlie.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ʹ��LinkedHashMapʵ�ִ�LRU�㷨�Ļ����
 * 
 * ����ز����У���ʼ������������������������ӣ�����ʱ��
 * ʹ�õ���ģʽ��������ض����趨һ������ʱ�䣬�ڲ�����һ����ʱ���߳�����������ڶ���
 * �����������С���������ʱ��һ�������ﵽ�������ӣ����Խ������ݣ�
 * ����������������������ʱ������ؽ�������ٱ����ʵĶ��󣬼�����Ԫ�أ�
 * ÿ��һ��������󱻷���ʱ���ͽ��䴴��ʱ������Ϊ��ǰ����ʱ�䣬Ŀ��������������ʱ�䣬�Ӷ���С����ʱ������ĸ���
 * @author charlie
 */
public class CachePool {
	private static CachePool instance; //�����Ψһʵ��
	private Map<Object,Object> map; //����Map
	private int maxCapacity; //������������
	private long cacheTimeout; //�������Ĺ���ʱ��
	
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
	 * �õ�Ψһʵ��
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
	 * �õ�Ψһʵ��
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
	 * ������л���ʵ��
	 */
	public synchronized void clearAllItems(){
		map.clear();
	}
	
	/**
	 * ��ӡ���еĻ���ʵ��
	 */
	public synchronized void printAllItems(){
		System.out.println(map);
	}
	
	/**
	 * ��ȡ����ʵ�壬ע��Ҫ���ô���ʱ��
	 * @param name
	 * @return
	 */
	public synchronized Object getCacheItem(Object name){
		if(!map.containsKey(name)){
			return null;
		}
		CacheItem cacheItem = (CacheItem) map.get(name);
		cacheItem.setCreateTime(); //���ô���ʱ��
		return cacheItem;
	}
	
	/**
	 * ��Ż���ʵ�壬ע���жϻ���������Ƿ�ﵽ����
	 * @param name
	 * @param obj
	 */
	public synchronized void putCacheItem(Object name,Object obj){
		if(!map.containsKey(name)){
			while(map.size() >= maxCapacity){
				System.out.print("�����������ɾ������Ϊ��");
				Object[] keys = map.keySet().toArray();
				System.out.println(keys[0]+"="+map.get(keys[0]));
				map.remove(keys[0]);
			}
			map.put(name, new CacheItem(obj));
		}
		//���key�Ѵ��ڣ������û���ʵ��ʹ���ʱ��
		CacheItem cacheItem = (CacheItem) map.get(name);
		cacheItem.setCreateTime();
		cacheItem.setEntity(obj);	
	}
	
	/**
	 * �Ƴ���������
	 * @param name
	 */
	public synchronized void removeCacheItem(Object name){
		if(!map.containsKey(name)){
			return;
		}
		map.remove(name);
	}
	
	/**
	 * ��ȡ�������ݵ�����
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
					System.out.println("������ڶ���󻺴����ʣ������У�\n"+map);
					Thread.sleep(cacheTimeout);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
