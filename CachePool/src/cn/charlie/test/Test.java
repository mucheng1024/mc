package cn.charlie.test;

import cn.charlie.cache.CachePool;

public class Test {

	public static void main(String[] args) throws Exception {
		CachePool cachePool = CachePool.getInstance(4, 5000);
		char c='a';
		for(int i=0;i<6;i++){
			cachePool.putCacheItem(i, (char)(c+i));
			Thread.sleep(2000);
		}
		cachePool.printAllItems();
		System.out.println("²éÕÒ£º3="+cachePool.getCacheItem(3));
		cachePool.printAllItems();
		Thread.sleep(3000);
		System.out.println("6="+cachePool.getCacheItem(6));
		System.out.println("²åÈë£º7=7777");
		cachePool.putCacheItem("7", "7777");
		cachePool.printAllItems();
	}
}
