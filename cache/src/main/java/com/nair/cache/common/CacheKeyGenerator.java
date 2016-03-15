/*
 * This File is  the sole property of Paytm(One97 Communications Limited)
 */
package com.nair.cache.common;

import java.util.concurrent.TimeUnit;

import com.nair.cache.expirable.ExpirableCacheKey;

public abstract class CacheKeyGenerator {

	public static<K> ExpirableCacheKey<K> getExpirableCacheKey(final long timeOut, final TimeUnit timeUnit, final K key){
		return new ExpirableCacheKey<K>(timeOut, timeUnit, key);
	}
}
