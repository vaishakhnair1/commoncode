/*
 * This File is  the sole property of Paytm(One97 Communications Limited)
 */
package com.nair.cache.expirable;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExpirableCacheCleaner<K, V> extends Thread {

	private final DelayQueue<ExpirableCacheKey<K>>						delayQueue;
	private final Map<ExpirableCacheKey<K>, ExpirableCacheValue<ExpirableCacheKey<K>, V>>	dataMap;

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(CacheCleaner.class);

	private final AtomicBoolean						stopIndicator	= new AtomicBoolean(Boolean.FALSE);

	public ExpirableCacheCleaner(final DelayQueue<ExpirableCacheKey<K>> delayQueue, final Map<ExpirableCacheKey<K>, ExpirableCacheValue<ExpirableCacheKey<K>, V>> dataMap) {
		this.delayQueue = delayQueue;
		this.dataMap = dataMap;
	}

	public void stopCleanUp() {
		stopIndicator.set(Boolean.TRUE);
	}

	@Override
	public void run() {
		try {
			while (!stopIndicator.get()) {
				final ExpirableCacheKey<K> k = delayQueue.take();
				if(k != null) {
					final ExpirableCacheValue<ExpirableCacheKey<K>, V> value = dataMap.get(k.getKey());
					if((value != null) && (value.getKey().getDelay(TimeUnit.MILLISECONDS) <= 0)) {
						dataMap.remove(k);
						System.out.println("Removing value" + k.toString());
						System.out.println("At " + new Date());
						System.out.println(dataMap);
					} else {
						System.out.println("Duplicate Key detected, with extended expiry. Not deleting" + k.toString());
					}
				}
			}
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

}
