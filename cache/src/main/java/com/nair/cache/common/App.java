/*
 * This File is  the sole property of Paytm(One97 Communications Limited)
 */
package com.nair.cache.common;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.nair.cache.expirable.ExpirableCache;
import com.nair.cache.expirable.ExpirableCacheKey;



/**
 * Hello world!
 *
 */
public class App {

	public static void main(final String[] args) throws InterruptedException {
		final ExpirableCache<ExpirableCacheKey<String>, String> cache = CacheFactory.getExpirableCache();
		final ExpirableCacheKey<String> cacheKey = CacheKeyGenerator.getExpirableCacheKey(10l, TimeUnit.SECONDS,  "key");
		final ExpirableCacheKey<String> cacheKey2 = CacheKeyGenerator.getExpirableCacheKey(5l, TimeUnit.SECONDS,  "key");
		final ExpirableCacheKey<String> cacheKey3 = CacheKeyGenerator.getExpirableCacheKey(2l, TimeUnit.SECONDS,  "key");
		final ExpirableCacheKey<String> cacheKey4 = CacheKeyGenerator.getExpirableCacheKey(7l, TimeUnit.SECONDS,  "key");
		cache.putValue(cacheKey, "value1");
		cache.putValue(cacheKey2, "value2");
		cache.putValue(cacheKey3, "value3");
		cache.putValue(cacheKey4, "value4");
		System.out.println(new Date().toString() + cache);
	}
}
