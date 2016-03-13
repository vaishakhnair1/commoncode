/**
 * 
 */
package com.nair.cache;

/**
 * @author vaishakh
 *
 */
public class ExpiringCacheValue<K extends ExpiringCacheKey<?>, V> {

	private K key;
	private V value;
	
	public ExpiringCacheValue(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * @return the value
	 */
	public V getValue() {
		return value;
	}
	
}
