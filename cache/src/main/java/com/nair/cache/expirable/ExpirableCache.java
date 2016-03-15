/*
 * This File is the sole property of Paytm(One97 Communications Limited)
 */
package com.nair.cache.expirable;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class ExpirableCache<K, V> {

	private Map<ExpirableCacheKey<K>, ExpirableCacheValue<ExpirableCacheKey<K>, V>>	dataMap;
	private DelayQueue<ExpirableCacheKey<K>>						delayQueue;
	private ExpirableCacheCleaner<K, V>			cacheCleaner;
	private final long timeOut;
	private final TimeUnit timeUnit;

	public ExpirableCache(final long timeOut, final TimeUnit timeUnit) {
		this.timeOut = timeOut;
		this.timeUnit = timeUnit;
		initiate();
	}

	public V getValue(final K k) {
		return dataMap.get(k).getValue();
	}

	public ExpirableCache<K, V> putValue(final K k, final V v, final long timeOut, final TimeUnit timeUnit) {
		final ExpirableCacheKey<K> expirableCacheKey = new ExpirableCacheKey<K>(timeOut, timeUnit, k);
		dataMap.put(expirableCacheKey, new ExpirableCacheValue<ExpirableCacheKey<K>, V>(expirableCacheKey, v));
		delayQueue.put(expirableCacheKey);
		return this;
	}

	public void putValue(final K key, final V value) {
		putValue(key, value, timeOut, timeUnit);
	}

	public ExpirableCache<K, V> putAll(final ExpirableCache<K, V> existingCache) {
		for (final Entry<ExpirableCacheKey<K>, ExpirableCacheValue<ExpirableCacheKey<K>, V>> a : existingCache.dataMap.entrySet()) {
			putValue(a.getKey().getKey(), a.getValue().getValue());
		}
		return this;
	}

	public void flushALl() {
		stopCleanup();
		initiate();
	}

	private void initiate() {
		dataMap = new ConcurrentHashMap<ExpirableCacheKey<K>, ExpirableCacheValue<ExpirableCacheKey<K>, V>>();
		delayQueue = new DelayQueue<ExpirableCacheKey<K>>();
		cacheCleaner = new ExpirableCacheCleaner<K, V>(delayQueue, dataMap);
		cacheCleaner.start();
	}

	public int getActiveElements() {
		return dataMap.size();
	}

	@Override
	public String toString() {
		return dataMap.toString();
	}

	public void shutdown() {
		stopCleanup();
		releaseData();
	}

	private void releaseData() {
		dataMap = null;
		delayQueue = null;
		cacheCleaner = null;
	}

	private void stopCleanup() {
		cacheCleaner.stopCleanUp();
		cacheCleaner.interrupt();
	}
}
