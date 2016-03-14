/**
 * 
 */
package com.nair.cache;

import java.util.concurrent.TimeUnit;

/**
 * @author vaishakh
 *
 */
public class ExpirableCacheKey<K> extends AbstractExpirableCacheKey {

	private K key;
	
	public ExpirableCacheKey(long delayTime, TimeUnit timeUnit, K key) {
		super(delayTime, timeUnit);
		this.key = key;
	}

	/**
	 * @return the data
	 */
	public K getKey() {
		return key;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CacheKey [key=").append(key).append(", expiryTime=").append(super.getExpiryTime()).append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		ExpirableCacheKey<K> other = (ExpirableCacheKey<K>) obj;
		if(key == null) {
			if(other.key != null)
				return false;
		} else if(!key.equals(other.key))
			return false;
		return true;
	}
	
	

}
