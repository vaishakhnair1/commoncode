package com.nair.cache;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;

public class ExpiringCache<K extends ExpiringCacheKey<?>, V> {

	private Map<K, ExpiringCacheValue<K, V>> dataMap;
	private DelayQueue<K> delayQueue;
	private CacheCleaner<K, V> cacheCleaner;
	
	public ExpiringCache() {
		initiate();
	}
	
	public V getValue(K k){
		return dataMap.get(k).getValue();
	}
	
	public ExpiringCache<K, V> putValue(K k, V v){
//		if(dataMap.containsKey(k)){
//			delayQueue.remove(k);
//		}
		dataMap.put(k, new ExpiringCacheValue<K, V>(k, v));
		delayQueue.put(k);
		System.out.println(delayQueue);
		return this;
	}
	
	public ExpiringCache<K, V> putAll(ExpiringCache<K, V> existingCache){
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
		cacheCleaner = new CacheCleaner<K, V>(delayQueue, dataMap);
		cacheCleaner.start();
	}

	@Override
	public String toString() {
		return dataMap.toString();
	}
	
}
