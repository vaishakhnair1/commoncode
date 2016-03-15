/*
 * This File is  the sole property of Paytm(One97 Communications Limited)
 */
package com.nair.cache.expirable;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;

public class ExpirableCache<K extends ExpirableCacheKey<?>, V>{

	private Map<K, ExpirableCacheValue<K, V>> dataMap;
	private DelayQueue<K> delayQueue;
	private ExpirableCacheCleaner<K, V> cacheCleaner;

	public ExpirableCache() {
		initiate();
	}

	public V getValue(final K k){
		return dataMap.get(k).getValue();
	}

	public ExpirableCache<K, V> putValue(final K k, final V v){
		dataMap.put(k, new ExpirableCacheValue<K, V>(k, v));
		delayQueue.put(k);
		return this;
	}

	public ExpirableCache<K, V> putAll(final ExpirableCache<K, V> existingCache){
		for(final Entry<K, ExpirableCacheValue<K, V>> a : existingCache.dataMap.entrySet()){
			putValue(a.getKey(), a.getValue().getValue());
		}
		return this;
	}

	public void flushALl(){
		stopCleanup();
		initiate();
	}

	private void initiate() {
		dataMap = new ConcurrentHashMap<K,  ExpirableCacheValue<K, V>>();
		delayQueue = new DelayQueue<K>();
		cacheCleaner = new ExpirableCacheCleaner<K, V>(delayQueue, dataMap);
		cacheCleaner.start();
	}

	public int getActiveElements(){
		return dataMap.size();
	}

	@Override
	public String toString() {
		return dataMap.toString();
	}

	public void shutdown(){
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
