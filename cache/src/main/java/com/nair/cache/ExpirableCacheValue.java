/**
 * 
 */
package com.nair.cache;

/**
 * @author vaishakh
 *
 */
public class ExpirableCacheValue<K extends ExpirableCacheKey<?>, V> {

	private K key;
	private V value;
	
	public ExpirableCacheValue(K key, V value) {
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
