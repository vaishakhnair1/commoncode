/*
 * This File is the sole property of Paytm(One97 Communications Limited)
 */
package com.nair.cache.common;

import java.util.concurrent.TimeUnit;

import javax.management.InstanceAlreadyExistsException;

import com.nair.cache.expirable.IExpirableCache;


/**
 * Hello world!
 * This class is the testing class to test the functionality.
 */
public class App {

	public static void main(final String[] args) throws InstanceAlreadyExistsException {
		final CacheManager cacheManager = CacheManager.getCacheManager();
		final IExpirableCache<String, String> testString = new TestStringImplementation<String, String>();
		final IExpirableCache<Integer, Integer> testInteger = new TestIntegerImplementation<Integer, Integer>();
		cacheManager.registerExpirableCache("testStringCache", String.class, String.class, 5, TimeUnit.SECONDS, testString);
		cacheManager.registerExpirableCache("testIntegerCache", Integer.class, Integer.class, 5, TimeUnit.SECONDS, testInteger);
		cacheManager.putValueToExpirableCache("key1", "value1", "testStringCache");
		cacheManager.putValueToExpirableCache("key2", "value2",1, TimeUnit.SECONDS, "testStringCache");
		final String value = cacheManager.getValueFromExpirableCache("key1", String.class, "testStringCache");
		System.out.println(value);
		cacheManager.putValueToExpirableCache(1, 1, "testIntegerCache");
		cacheManager.putValueToExpirableCache(2, 2,1, TimeUnit.SECONDS, "testIntegerCache");
		final Integer value1 = cacheManager.getValueFromExpirableCache(1, Integer.class, "testIntegerCache");
		System.out.println(value1);

	}
}
