/*
 * This File is the sole property of Paytm(One97 Communications Limited)
 */
package com.nair.cache.expirable;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This Class is used for Clean Up of the Expirable Cache. It runs as a
 * Background thread.
 *
 * @author vaishakh
 *
 * @param <K>
 *            the type of Keys that are being stored in the Cache.
 * @param <V>
 *            the type of Values that are being mapped to.
 */
public class ExpirableCacheCleaner<K, V> extends Thread {

	/**
	 * The queue which tracks the keys and their expiry.
	 */
	private final DelayQueue<ExpirableCacheKey<K>>											delayQueue;

	/**
	 * The Map which stores mapping of keys to values
	 */
	private final Map<ExpirableCacheKey<K>, ExpirableCacheValue<ExpirableCacheKey<K>, V>>	dataMap;

	/**
	 * The Logger.
	 */
	private static final Logger																LOGGER			= LoggerFactory
																													.getLogger(ExpirableCacheCleaner.class);

	/**
	 * The StopIndicator
	 */
	private volatile AtomicBoolean															stopIndicator	= new AtomicBoolean(
																													Boolean.FALSE);

	/**
	 * Used to create an instance of the cache cleaner
	 * 
	 * @param delayQueue
	 *            the queue which tracks expiry of keys
	 * @param dataMap
	 *            the map which stores mapping of keys to values
	 */
	public ExpirableCacheCleaner(final DelayQueue<ExpirableCacheKey<K>> delayQueue,
			final Map<ExpirableCacheKey<K>, ExpirableCacheValue<ExpirableCacheKey<K>, V>> dataMap) {
		this.delayQueue = delayQueue;
		this.dataMap = dataMap;
	}

	/**
	 * Used to stop clean up of keys
	 */
	public void stopCleanUp() {
		stopIndicator.set(Boolean.TRUE);
	}

	@Override
	public void run() {
		try {
			while (!stopIndicator.get()) {
				final ExpirableCacheKey<K> k = delayQueue.take();
				if(k != null) {
					final ExpirableCacheValue<ExpirableCacheKey<K>, V> value = dataMap.get(k);
					if((value != null) && (value.getKey().getDelay(TimeUnit.MILLISECONDS) <= 0)) {
						dataMap.remove(k);
						LOGGER.info("Removing value ::{}:: at ::{}::", k.toString(), new Date());
					} else {
						LOGGER.info("Duplicate Key detected, with extended expiry. Not deleting ::{}::", k.toString());
					}
				}
			}
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

}
