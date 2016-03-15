/*
 * This File is the sole property of Paytm(One97 Communications Limited)
 */
package com.nair.cache.common;

import java.util.concurrent.TimeUnit;

import com.nair.cache.expirable.ExpirableCache;

/**
 * This class acts as the factory which provides the user
 *
 * @author vaishakh
 *
 */
public abstract class CacheFactory {

	public static <K, V> ExpirableCache<K, V> getExpirableCache(final long timeOut, final TimeUnit timeUnit) {
		return new ExpirableCache<K, V>(timeOut, timeUnit);
	}
}
