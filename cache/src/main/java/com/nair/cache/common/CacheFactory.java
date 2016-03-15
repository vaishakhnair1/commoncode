/*
 * This File is the sole property of Paytm(One97 Communications Limited)
 */
package com.nair.cache.common;

import java.util.concurrent.TimeUnit;

import com.nair.cache.expirable.ExpirableCache;

/**
 * This class acts as the factory which provides the user an instance of the
 * CacheType that is required.
 *
 * @author vaishakh
 *
 */
public abstract class CacheFactory {

	/**
	 * This method is used to get a new Instance of an Expirable cache
	 *
	 * @param timeOut
	 *            The Time Delay before the key gets deleted from the Cache
	 * @param timeUnit
	 *            The time units in which delay is calculated.
	 * @return a new instance of the ExpirableCache
	 */
	public static <K, V> ExpirableCache<K, V> getExpirableCache(final long timeOut, final TimeUnit timeUnit) {
		return new ExpirableCache<K, V>(timeOut, timeUnit);
	}

	/**
	 * This method is used to get a new Instance of an Expirable cache
	 *
	 * @return a new instance of the ExpirableCache
	 */
	public static <K, V> ExpirableCache<K, V> getExpirableCache() {
		return getExpirableCache(15, TimeUnit.MINUTES);
	}
}
