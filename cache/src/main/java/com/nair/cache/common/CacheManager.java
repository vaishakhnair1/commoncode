/**
 *
 */
package com.nair.cache.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.management.InstanceAlreadyExistsException;

import com.nair.cache.expirable.ExpirableCache;
import com.nair.cache.expirable.IExpirableCache;

/**
 * @author vaishakh This is the Manager class for the Caching Solution. It
 *         abstracts the internal functionalities from the consumer of the APIs.
 *         It eagerly loads an instance of itself, which contains a Registry for
 *         all caches registered, and secondary data sources for those which
 *         have been supplied.
 */
public final class CacheManager {

	static {
		CACHE_MANAGER = new CacheManager();
		EXPIRABLE_CACHE_REGISTRY = new HashMap<String, ExpirableCache<Object, Object>>(0);
		SECONDARY_DATA_SOURCE = new HashMap<String, IExpirableCache<Object, Object>>(0);
	}

	/**
	 * The Instance that is shared across the JVM
	 */
	private static final CacheManager									CACHE_MANAGER;

	/**
	 * The Registry for the Expirable Cache
	 */
	private static final Map<String, ExpirableCache<Object, Object>>	EXPIRABLE_CACHE_REGISTRY;

	/**
	 * The Registry for the Secondary data sources for all Expirable Caches that
	 * have been procided.
	 */
	private static final Map<String, IExpirableCache<Object, Object>>	SECONDARY_DATA_SOURCE;

	private CacheManager() {
	}

	/**
	 * Used to fetch an instance of the Manager.
	 * 
	 * @return the single instance of {@code CacheManager} that is shared across
	 *         the JVM}
	 */
	public static CacheManager getCacheManager() {
		return CACHE_MANAGER;
	}

	/**
	 * Used to register an Expirable Cache with the Registry
	 * 
	 * @param expirableCacheName
	 *            the name with which the expirable cache is to be registered
	 * @param keyClass
	 *            the class which acts as the key
	 * @param valueClass
	 *            the value which acts as the value
	 * @throws InstanceAlreadyExistsException
	 *             if an expirable cache with the same name has already been
	 *             registered.
	 */
	public <K, V> void registerExpirableCache(final String expirableCacheName, final Class<K> keyClass, final Class<V> valueClass)
			throws InstanceAlreadyExistsException {
		if(!EXPIRABLE_CACHE_REGISTRY.containsKey(expirableCacheName)) {
			final ExpirableCache<K, V> expirableCache = CacheFactory.getExpirableCache();
			registerExpirableCache(expirableCacheName, expirableCache);
		} else {
			throw new InstanceAlreadyExistsException("Cache with same name has already been registered beforehand.");
		}
	}

	/**
	 * Used to register an Expirable Cache with the Registry
	 * 
	 * @param expirableCacheName
	 *            the name with which the expirable cache is to be registered
	 * @param keyClass
	 *            the class which acts as the key
	 * @param valueClass
	 *            the value which acts as the value
	 * @param timeOut
	 *            the time out for the keys
	 * @param timeUnit
	 *            the Units in which above time out is calculated
	 * @throws InstanceAlreadyExistsException
	 *             if an expirable cache with the same name has already been
	 *             registered.
	 */
	public <K, V> void registerExpirableCache(final String expirableCacheName, final Class<K> keyClass, final Class<V> valueClass,
			final long timeOut, final TimeUnit timeUnit) throws InstanceAlreadyExistsException {
		if(!EXPIRABLE_CACHE_REGISTRY.containsKey(expirableCacheName)) {
			final ExpirableCache<K, V> expirableCache = CacheFactory.getExpirableCache(timeOut, timeUnit);
			registerExpirableCache(expirableCacheName, expirableCache);
		} else {
			throw new InstanceAlreadyExistsException("Cache with same name has already been registered beforehand.");
		}
	}

	/**
	 * Used to register an Expirable Cache with the Registry
	 * 
	 * @param expirableCacheName
	 *            the name with which the expirable cache is to be registered
	 * @param keyClass
	 *            the class which acts as the key
	 * @param valueClass
	 *            the value which acts as the value
	 * @param secondaryDataSource
	 *            The secondary data source from which data is to be fetched if
	 *            value is not found in Cache. If value is found in secondary
	 *            data source, then same will be populated to Cache, with
	 *            timeout.
	 * @throws InstanceAlreadyExistsException
	 *             if an expirable cache with the same name has already been
	 *             registered.
	 */
	public <K, V> void registerExpirableCache(final String expirableCacheName, final Class<K> keyClass, final Class<V> valueClass,
			final IExpirableCache<K, V> secondaryDataSource) throws InstanceAlreadyExistsException {
		if(!EXPIRABLE_CACHE_REGISTRY.containsKey(expirableCacheName)) {
			final ExpirableCache<K, V> expirableCache = CacheFactory.getExpirableCache();
			registerExpirableCache(expirableCacheName, expirableCache);
			registerSecondaryDataSource(expirableCacheName, secondaryDataSource);
		} else {
			throw new InstanceAlreadyExistsException("Cache with same name has already been registered beforehand.");
		}
	}

	/**
	 * Used to register an Expirable Cache with the Registry
	 * 
	 * @param expirableCacheName
	 *            the name with which the expirable cache is to be registered
	 * @param keyClass
	 *            the class which acts as the key
	 * @param valueClass
	 *            the value which acts as the value
	 * @param timeOut
	 *            the time out for the keys
	 * @param timeUnit
	 *            the Units in which above time out is calculated
	 * @param secondaryDataSource
	 *            The secondary data source from which data is to be fetched if
	 *            value is not found in Cache. If value is found in secondary
	 *            data source, then same will be populated to Cache, with
	 *            timeout.
	 * @throws InstanceAlreadyExistsException
	 *             if an expirable cache with the same name has already been
	 *             registered.
	 */
	public <K, V> void registerExpirableCache(final String expirableCacheName, final Class<K> keyClass, final Class<V> valueClass,
			final long timeOut, final TimeUnit timeUnit, final IExpirableCache<K, V> secondaryDataSource)
			throws InstanceAlreadyExistsException {
		if(!EXPIRABLE_CACHE_REGISTRY.containsKey(expirableCacheName)) {
			final ExpirableCache<K, V> expirableCache = CacheFactory.getExpirableCache(timeOut, timeUnit);
			registerExpirableCache(expirableCacheName, expirableCache);
			registerSecondaryDataSource(expirableCacheName, secondaryDataSource);
		} else {
			throw new InstanceAlreadyExistsException("Cache with same name has already been registered beforehand.");
		}
	}

	/**
	 * Used internally to register a Secondary Data Source
	 * 
	 * @param expirableCacheName
	 *            the name with which the expirable cache is to be registered
	 * @param secondaryDataSource
	 *            The secondary data source from which data is to be fetched if
	 *            value is not found in Cache. If value is found in secondary
	 *            data source, then same will be populated to Cache, with
	 *            timeout.
	 */
	@SuppressWarnings("unchecked")
	private <K, V> void registerSecondaryDataSource(final String expirableCacheName, final IExpirableCache<K, V> secondaryDataSource) {
		SECONDARY_DATA_SOURCE.put(expirableCacheName, (IExpirableCache<Object, Object>) secondaryDataSource);
	}

	/**
	 * Used internally to register an Expirable Cache
	 * 
	 * @param expirableCacheName
	 *            the name with which the expirable cache is to be registered
	 * @param expirableCache
	 *            the instance of the Expirable Cache
	 */
	@SuppressWarnings("unchecked")
	private <K, V> void registerExpirableCache(final String expirableCacheName, final ExpirableCache<K, V> expirableCache) {
		EXPIRABLE_CACHE_REGISTRY.put(expirableCacheName, (ExpirableCache<Object, Object>) expirableCache);
	}

	/**
	 * Used to fetch a Value from the Expirable Cache. If Value is not found in
	 * Cache, and Secondary Data Source is configured for the cache, value is
	 * fetched from the same and populated in Cache.
	 * 
	 * @param k
	 *            the Key.
	 * @param clazz
	 *            The class in which response is expected
	 * @param registeredExpirableCache
	 *            The name with which the cache has been registered.
	 * @return The value that is present in the cache, or one that was loaded
	 *         from Secondary Data Source(If configured)
	 */
	public <K, V> V getValueFromExpirableCache(final K k, final Class<V> clazz, final String registeredExpirableCache) {
		final Object obj = EXPIRABLE_CACHE_REGISTRY.get(registeredExpirableCache).getValue(k);
		V v = clazz.cast(null);
		if(obj != null) {
			v = clazz.cast(obj);
		} else if(SECONDARY_DATA_SOURCE.containsKey(registeredExpirableCache)) {
			final Object dataFromSecondary = SECONDARY_DATA_SOURCE.get(registeredExpirableCache).getValue(k);
			if(dataFromSecondary != null) {
				v = clazz.cast(dataFromSecondary);
				putValueToExpirableCache(k, v, registeredExpirableCache);
			}
		}
		return v;
	}

	/**
	 * Used to add a single Value to a Registered Expirable Cache
	 * 
	 * @param k
	 *            the key
	 * @param v
	 *            the value
	 * @param registeredExpirableCache
	 *            The name with which the cache has been registered.
	 */
	public <K, V> void putValueToExpirableCache(final K k, final V v, final String registeredExpirableCache) {
		EXPIRABLE_CACHE_REGISTRY.get(registeredExpirableCache).putValue(k, v);
	}

	/**
	 * Used to add a single Value to a Registered Expirable Cache with specific
	 * timeout
	 * 
	 * @param k
	 *            the key
	 * @param v
	 *            the value
	 * @param timeOut
	 *            the timeout for the key
	 * @param timeUnit
	 *            the units in which timeout is calculated
	 * @param registeredExpirableCache
	 *            The name with which the cache has been registered.
	 */
	public <K, V> void putValueToExpirableCache(final K k, final V v, final long timeOut, final TimeUnit timeUnit,
			final String registeredExpirableCache) {
		EXPIRABLE_CACHE_REGISTRY.get(registeredExpirableCache).putValue(k, v, timeOut, timeUnit);
	}

	/**
	 * Used to populate a registered cache from an already existing Cache.
	 * 
	 * @param expirableCache
	 *            The existing cache
	 * @param registeredExpirableCache
	 *            The name with which the cache has been registered.
	 */
	public <K, V> void putAllValuesToExpirableCache(final ExpirableCache<Object, Object> expirableCache,
			final String registeredExpirableCache) {
		EXPIRABLE_CACHE_REGISTRY.get(registeredExpirableCache).putAll(expirableCache);
	}

	/**
	 * Used to Flush a registered cache
	 * 
	 * @param registeredExpirableCache
	 *            The name with which the cache has been registered.
	 */
	public void flushAll(final String registeredExpirableCache) {
		EXPIRABLE_CACHE_REGISTRY.get(registeredExpirableCache).flushALl();
	}

	/**
	 * The number of elements that are Cached in a registered expirable cache
	 * 
	 * @param registeredExpirableCache
	 *            The name with which the cache has been registered.
	 * @return number of elements in specific cache
	 */
	public int getActiveElements(final String registeredExpirableCache) {
		return EXPIRABLE_CACHE_REGISTRY.get(registeredExpirableCache).getActiveElements();
	}

	/**
	 * Used to Shutdown a Registered Expirable Cache
	 * 
	 * @param registeredExpirableCache
	 *            The name with which the cache has been registered.
	 */
	public void shutdown(final String registeredExpirableCache) {
		EXPIRABLE_CACHE_REGISTRY.get(registeredExpirableCache).shutdown();
		EXPIRABLE_CACHE_REGISTRY.remove(registeredExpirableCache);
		if(SECONDARY_DATA_SOURCE.containsKey(registeredExpirableCache)) {
			SECONDARY_DATA_SOURCE.remove(registeredExpirableCache);
		}
	}

}
