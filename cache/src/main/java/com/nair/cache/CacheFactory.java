package com.nair.cache;

public abstract class CacheFactory {

	public static<K, V> ExpirableCache<ExpirableCacheKey<K>, V> getExpirableCache(){
		return new ExpirableCache<ExpirableCacheKey<K>, V>();
	}
}
