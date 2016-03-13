package com.nair.cache;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;

public class ExpirableCache<K extends ExpirableCacheKey<?>, V> {

	private Map<K, ExpiringCacheValue<K, V>> dataMap;
	private DelayQueue<K> delayQueue;
	private ExpirableCacheCleaner<K, V> cacheCleaner;
	
	public ExpirableCache() {
		initiate();
	}
	
	public V getValue(K k){
		return dataMap.get(k).getValue();
	}
	
	public ExpirableCache<K, V> putValue(K k, V v){
//		if(dataMap.containsKey(k)){
//			delayQueue.remove(k);
//		}
		dataMap.put(k, new ExpiringCacheValue<K, V>(k, v));
		delayQueue.put(k);
		System.out.println(delayQueue);
		return this;
	}
	
	public ExpirableCache<K, V> putAll(ExpirableCache<K, V> existingCache){
		for(Entry<K, ExpiringCacheValue<K, V>> a : existingCache.dataMap.entrySet()){
			putValue(a.getKey(), a.getValue().getValue());
		}
		return this;
	}

	public void flushALl(){
		cacheCleaner.stopCleanUp();
		cacheCleaner.interrupt();
		initiate();
	}

	private void initiate() {
		dataMap = new ConcurrentHashMap<K,  ExpiringCacheValue<K, V>>();
		delayQueue = new DelayQueue<K>();
		cacheCleaner = new ExpirableCacheCleaner<K, V>(delayQueue, dataMap);
		cacheCleaner.start();
	}

	@Override
	public String toString() {
		return dataMap.toString();
	}
	
}
