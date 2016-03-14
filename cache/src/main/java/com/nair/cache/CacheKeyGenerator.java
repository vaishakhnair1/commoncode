package com.nair.cache;

import java.util.concurrent.TimeUnit;

public abstract class CacheKeyGenerator {

	public static<K> ExpirableCacheKey<K> getExpirableCacheKey(long timeOut, TimeUnit timeUnit, K key){
		return new ExpirableCacheKey<K>(timeOut, timeUnit, key);
	}
}
