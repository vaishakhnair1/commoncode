/*
 * This File is the sole property of Paytm(One97 Communications Limited)
 */
package com.nair.cache.enums;

import java.security.InvalidParameterException;

/**
 * The types of Caches that will be supported. Currently, only Expirable Cache
 * is supported and implemented.
 * 
 * @author vaishakh
 *
 */
public enum CacheType {

	EXPIRABLE_CACHE("EXPIRABLE_CACHE"), UNBOUNDED_CACHE("UNBOUNDED_CACHE"), LRU_CACHE("LRU_CACHE");

	String	cacheType;

	private CacheType(final String cacheType) {
		this.cacheType = cacheType;
	}

	/**
	 * Used to fetch the {@code CacheType} based on the String value
	 * 
	 * @param cacheType
	 *            the String representation
	 * @return the CacheTypes corresponding
	 * @throws InvalidParameterException
	 *             if the value is not a valid {@code CacheType}
	 */
	public static CacheType getCacheType(final String cacheType) throws InvalidParameterException {
		for (final CacheType cacheTypes : CacheType.values()) {
			if(cacheTypes.cacheType.equals(cacheType)) {
				return cacheTypes;
			}
		}
		throw new InvalidParameterException();
	}

}
