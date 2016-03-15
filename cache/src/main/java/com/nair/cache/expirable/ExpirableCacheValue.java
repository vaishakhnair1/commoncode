/**
 *
 */
package com.nair.cache.expirable;

/**
 * Used to wrap the values that are to be stored in an {@code ExpirableCache}
 * @author vaishakh
 *
 */
public class ExpirableCacheValue<K extends ExpirableCacheKey<?>, V> {

	/**
	 * The Key
	 */
	private final K	key;

	/**
	 * The value
	 */
	private final V	value;

	/**
	 * Used to get an instance of {@code ExpirableCacheValue}
	 * @param key the key
	 * @param value the value
	 */
	public ExpirableCacheValue(final K key, final V value) {
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
