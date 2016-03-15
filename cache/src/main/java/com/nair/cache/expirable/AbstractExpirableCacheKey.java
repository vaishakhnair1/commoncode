/**
 *
 */
package com.nair.cache.expirable;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * This class is the Abstract Expirable Cache Key, and contains all logic of
 * when Key is to be expired.
 * 
 * @author vaishakh
 *
 */
public abstract class AbstractExpirableCacheKey implements Delayed {

	/**
	 * The expiry time in Nano Seconds
	 */
	private final long	expiryTime;

	/**
	 * Used to intialize the expiry time of the Key
	 * 
	 * @param delayTime
	 *            the time period after which Key is to be expired.
	 * @param timeUnit
	 *            the units in which above delay is calculated
	 */
	public AbstractExpirableCacheKey(final long delayTime, final TimeUnit timeUnit) {
		this.expiryTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delayTime, timeUnit);
	}

	@Override
	public int compareTo(final Delayed o) {
		return (expiryTime - ((AbstractExpirableCacheKey) o).expiryTime) > 0 ? 1 : -1;
	}

	@Override
	public long getDelay(final TimeUnit unit) {
		return expiryTime - System.nanoTime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("AbtractExpiringCacheKey [expiryTime=").append(expiryTime).append("]");
		return builder.toString();
	}

	/**
	 * @return the expiryTime
	 */
	public long getExpiryTime() {
		return expiryTime;
	}
}
