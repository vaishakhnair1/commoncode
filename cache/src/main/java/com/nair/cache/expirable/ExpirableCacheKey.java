/**
 *
 */
package com.nair.cache.expirable;

import java.util.concurrent.TimeUnit;

/**
 * This class is the wrapper class for all keys stored in an {@code ExpirableCache}
 * @author vaishakh
 *
 */
public class ExpirableCacheKey<K> extends AbstractExpirableCacheKey {

	/**
	 * The key that is to be stored
	 */
	private K	key;

	/**
	 * used to create an instance of the {@code ExpirableCacheKey}
	 * @param delayTime the delay time for the key
	 * @param timeUnit the units in which delay time is calculated
	 * @param key the key which is to be wrapped
	 */
	public ExpirableCacheKey(final long delayTime, final TimeUnit timeUnit, final K key) {
		super(delayTime, timeUnit);
		this.key = key;
	}

	/**
	 * used to create an instance of the {@code ExpirableCacheKey}
	 * @param k the key which is to be wrapped
	 */
	public ExpirableCacheKey(final K k) {
		this(15, TimeUnit.MINUTES, k);
	}

	/**
	 * @return the data
	 */
	public K getKey() {
		return key;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("CacheKey [key=").append(key).append(", expiryTime=").append(super.getExpiryTime()).append("]");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(final Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		final ExpirableCacheKey<K> other = (ExpirableCacheKey<K>) obj;
		if(key == null) {
			if(other.key != null) {
				return false;
			}
		} else if(!key.equals(other.key)) {
			return false;
		}
		return true;
	}

}
