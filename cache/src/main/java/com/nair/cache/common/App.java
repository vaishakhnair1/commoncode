/*
 * This File is the sole property of Paytm(One97 Communications Limited)
 */
package com.nair.cache.common;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.nair.cache.expirable.ExpirableCache;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(final String[] args) throws InterruptedException {
		final ExpirableCache<String, String> cache = CacheFactory.getExpirableCache(10L, TimeUnit.SECONDS);
		cache.putValue("key", "value1");
		cache.putValue("key", "value2");
		cache.putValue("key", "value3");
		cache.putValue("key", "value4");
		System.out.println(new Date().toString() + cache);
	}
}
