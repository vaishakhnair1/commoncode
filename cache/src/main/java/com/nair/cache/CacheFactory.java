package com.nair.cache;

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
