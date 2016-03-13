package com.nair.cache;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExpirableCacheCleaner<K extends ExpirableCacheKey<?>, V> extends Thread{

	private DelayQueue<K>	delayQueue;
	private Map<K, ExpiringCacheValue<K, V>> dataMap;
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(CacheCleaner.class);
	
	private AtomicBoolean stopIndicator = new AtomicBoolean(Boolean.FALSE);
	
	public ExpirableCacheCleaner(DelayQueue<K> delayQueue, Map<K, ExpiringCacheValue<K, V>> dataMap) {
		this.delayQueue = delayQueue;
		this.dataMap = dataMap;
	}

	public void stopCleanUp(){
		stopIndicator.set(Boolean.TRUE);
	}
	
	@Override
	public void run() {
		try {
			while (!stopIndicator.get()) {
				K k = delayQueue.take();
				if(k != null) {
					ExpiringCacheValue<K, V> value = dataMap.get(k);
					if( value != null && value.getKey().getDelay(TimeUnit.MILLISECONDS) <= 0) {
						dataMap.remove(k);
						System.out.println("Removing value" + k.toString());
						System.out.println("At " + new Date());
						System.out.println(dataMap);
					} else {
						System.out.println("Duplicate Key detected, with extended expiry. Not deleting" + k.toString());
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
