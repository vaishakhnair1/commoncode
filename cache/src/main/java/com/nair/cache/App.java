package com.nair.cache;

import java.util.Date;
import java.util.concurrent.TimeUnit;



/**
 * Hello world!
 *
 */
public class App {

//	public static void main(String[] args) {
//		System.out.println("Hello World!");
//		DelayQueue<ExpiringCacheKey<String>> delayQueue = new DelayQueue<ExpiringCacheKey<String>>();
//		ExpiringCacheKey<String> key = new ExpiringCacheKey<String>(System.currentTimeMillis() + 2000L, "data1");
//		ExpiringCacheKey<String> key2 = new ExpiringCacheKey<String>(System.currentTimeMillis() + 10000L, "data1");
//		System.out.println(System.currentTimeMillis());
//		delayQueue.put(key);
//		delayQueue.put(key2);
//		while(!delayQueue.isEmpty()){
//			System.out.println(System.currentTimeMillis());
//			try {
//				System.out.println(delayQueue.take().toString() + System.currentTimeMillis());
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	
	public static void main(String[] args) throws InterruptedException {
		ExpirableCache<ExpirableCacheKey<String>, String> cache = CacheFactory.getExpirableCache();
		ExpirableCacheKey<String> cacheKey = new ExpirableCacheKey<String>(10l, TimeUnit.SECONDS,  "key");
		ExpirableCacheKey<String> cacheKey2 = new ExpirableCacheKey<String>(5l, TimeUnit.SECONDS,  "key");
		ExpirableCacheKey<String> cacheKey3 = new ExpirableCacheKey<String>(2l, TimeUnit.SECONDS,  "key");
		ExpirableCacheKey<String> cacheKey4 = new ExpirableCacheKey<String>(7l, TimeUnit.SECONDS,  "key");
		cache.putValue(cacheKey, "value1");
		cache.putValue(cacheKey2, "value2");
		cache.putValue(cacheKey3, "value3");
		cache.putValue(cacheKey4, "value4");
		System.out.println(new Date().toString() + cache);
//		cache.flushALl();
//		System.out.println(new Date().toString() + cache);
	}
}
