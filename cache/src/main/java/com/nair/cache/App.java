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
		ExpiringCache<ExpiringCacheKey<String>, String> cache = new ExpiringCache<ExpiringCacheKey<String>, String>();
		ExpiringCacheKey<String> cacheKey = new ExpiringCacheKey<String>(10l, TimeUnit.SECONDS,  "key");
		ExpiringCacheKey<String> cacheKey2 = new ExpiringCacheKey<String>(5l, TimeUnit.SECONDS,  "key");
		ExpiringCacheKey<String> cacheKey3 = new ExpiringCacheKey<String>(2l, TimeUnit.SECONDS,  "key");
		ExpiringCacheKey<String> cacheKey4 = new ExpiringCacheKey<String>(7l, TimeUnit.SECONDS,  "key");
		cache.putValue(cacheKey, "value1");
		cache.putValue(cacheKey2, "value2");
		cache.putValue(cacheKey3, "value3");
		cache.putValue(cacheKey4, "value4");
		System.out.println(new Date().toString() + cache);
//		cache.flushALl();
//		System.out.println(new Date().toString() + cache);
	}
}
