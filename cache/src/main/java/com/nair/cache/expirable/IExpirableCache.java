/**
 *
 */
package com.nair.cache.expirable;

/**
 * This is the Global interface for an Expirable Cache. It is implemented by
 * {@code ExpirableCache}. It must be implemented by any secondary data sources
 * that wish to supply data to an {@code ExpirableCache}
 * 
 * @author vaishakh
 */
public interface IExpirableCache<K, V> {

	public V getValue(K k);
}
