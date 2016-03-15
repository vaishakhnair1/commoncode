/*
 * This File is the sole property of Paytm(One97 Communications Limited)
 */
package com.nair.cache.expirable;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * The storage engine for an Expirable Cache.
 *
 * @author vaishakh
 *
 * @param <K>
 *            the types of Keys that are stored in the Map. Keys get wrapped in
 *            an instance of {@code ExpirableCacheKey}
 * @param <V>
 *            the types of Values that are mapped. Values get wrapped in an
 *            instance of {@code ExpirableCacheValue}
 */
public class ExpirableCache<K, V> implements IExpirableCache<K, V> {

	/**
	 * The mapping of keys to values
	 */
	private Map<ExpirableCacheKey<K>, ExpirableCacheValue<ExpirableCacheKey<K>, V>>	dataMap;

	/**
	 * The queue which manages expiry of all keys
	 */
	private DelayQueue<ExpirableCacheKey<K>>										delayQueue;

	/**
	 * The Parallel thread which is used to remove keys from the Cache that have
	 * been expired
	 */
	private ExpirableCacheCleaner<K, V>												cacheCleaner;

	/**
	 * The default TTL on keys in the Cache
	 */
	private final long																timeOut;

	/**
	 * The units in which above TTL is calculated
	 */
	private final TimeUnit															timeUnit;

	/**
	 * Used to create an instance of the Expirable Cache
	 *
	 * @param timeOut
	 *            the default TTL for each Key
	 * @param timeUnit
	 *            the units in which TTL is calculated
	 */
	public ExpirableCache(final long timeOut, final TimeUnit timeUnit) {
		this.timeOut = timeOut;
		this.timeUnit = timeUnit;
		initiate();
	}

	@SuppressWarnings("null")
	@Override
	public V getValue(final K k) {
		final ExpirableCacheKey<K> cacheKey = new ExpirableCacheKey<K>(k);
		return dataMap.containsKey(cacheKey) ? dataMap.get(cacheKey).getValue() : ((V) null);
	}

	/**
	 * Used to Put a value to the cache
	 *
	 * @param k
	 *            the key. This gets wrapped in {@code ExpirableCacheKey}
	 * @param v
	 *            the value. This gets wrapped in {@code ExpirableCacheValue}
	 * @param timeOut
	 *            The TTL to be set on the key. It overrides the default TTL.
	 * @param timeUnit
	 *            the units in which above TTL is calculated.
	 */
	public void putValue(final K k, final V v, final long timeOut, final TimeUnit timeUnit) {
		final ExpirableCacheKey<K> expirableCacheKey = new ExpirableCacheKey<K>(timeOut, timeUnit, k);
		dataMap.put(expirableCacheKey, new ExpirableCacheValue<ExpirableCacheKey<K>, V>(expirableCacheKey, v));
		delayQueue.put(expirableCacheKey);
	}

	/**
	 * Used to Put a value to the cache
	 *
	 * @param key
	 *            the key. This gets wrapped in {@code ExpirableCacheKey}
	 * @param value
	 *            the value. This gets wrapped in {@code ExpirableCacheValue}
	 */
	public void putValue(final K key, final V value) {
		putValue(key, value, timeOut, timeUnit);
	}

	/**
	 * Used to populate this Cache with data from an already existing Expirable
	 * Cache
	 *
	 * @param existingCache
	 *            the existing Expirable Cache
	 */
	public void putAll(final ExpirableCache<K, V> existingCache) {
		for (final Entry<ExpirableCacheKey<K>, ExpirableCacheValue<ExpirableCacheKey<K>, V>> a : existingCache.dataMap.entrySet()) {
			putValue(a.getKey().getKey(), a.getValue().getValue());
		}
	}

	/**
	 * Used to Flush the data stored in the Cache
	 */
	public void flushALl() {
		stopCleanup();
		initiate();
	}

	/**
	 * Used to initiate the storage engine for the Expirable Cache
	 */
	private void initiate() {
		dataMap = new ConcurrentHashMap<ExpirableCacheKey<K>, ExpirableCacheValue<ExpirableCacheKey<K>, V>>();
		delayQueue = new DelayQueue<ExpirableCacheKey<K>>();
		cacheCleaner = new ExpirableCacheCleaner<K, V>(delayQueue, dataMap);
		cacheCleaner.start();
	}

	/**
	 * Used to get the number of elements currently residing in the cache
	 *
	 * @return the number of cached elements
	 */
	public int getActiveElements() {
		return dataMap.size();
	}

	@Override
	public String toString() {
		return dataMap.toString();
	}

	/**
	 * Used to shutdown the cache. This finalizes the Cache. <strong> Only call
	 * this method if this Cache is no longer required in the lifecycle of the
	 * application. It cannot be used after this method has been
	 * invoked.</strong>
	 */
	public void shutdown() {
		stopCleanup();
		releaseData();
	}

	/**
	 * Releases all data stored in the Cache
	 */
	private void releaseData() {
		dataMap = null;
		delayQueue = null;
		cacheCleaner = null;
	}

	/**
	 * Stops clean up of the Cache
	 */
	private void stopCleanup() {
		cacheCleaner.stopCleanUp();
		cacheCleaner.interrupt();
	}
}
