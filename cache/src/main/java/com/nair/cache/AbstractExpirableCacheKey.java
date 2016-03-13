/**
 * 
 */
package com.nair.cache;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author vaishakh
 *
 */
public abstract class AbstractExpirableCacheKey implements Delayed{

	/**
	 * The expiry time in Nano Seconds
	 */
	private long expiryTime;
	
	public AbstractExpirableCacheKey(long delayTime, TimeUnit timeUnit) {
		this.expiryTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delayTime, timeUnit);
	}

	@Override
	public int compareTo(Delayed o) {
		return expiryTime - ((AbstractExpirableCacheKey) o).expiryTime > 0 ? 1 : -1;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return expiryTime - System.nanoTime();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
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
