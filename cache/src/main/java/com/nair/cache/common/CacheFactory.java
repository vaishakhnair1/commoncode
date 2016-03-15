/*
 * This File is  the sole property of Paytm(One97 Communications Limited)
 */
package com.nair.cache.common;

import com.nair.cache.expirable.ExpirableCache;
import com.nair.cache.expirable.ExpirableCacheKey;

/**
 * This class acts as the factory which provides the user
 * @author vaishakh
 *
 */
public abstract class CacheFactory {

	public static<K, V> ExpirableCache<ExpirableCacheKey<K>, V> getExpirableCache(){
		return new ExpirableCache<ExpirableCacheKey<K>, V>();
	}
}
