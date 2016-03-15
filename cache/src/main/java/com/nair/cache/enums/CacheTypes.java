/*
 * This File is the sole property of Paytm(One97 Communications Limited)
 */
package com.nair.cache.enums;

import java.security.InvalidParameterException;

public enum CacheTypes {

	EXPIRABLE_CACHE("EXPIRABLE_CACHE"), UNBOUNDED_CACHE("UNBOUNDED_CACHE"), LRU_CACHE("LRU_CACHE");

	String	cacheType;

	private CacheTypes(final String cacheType) {
		this.cacheType = cacheType;
	}

	public static CacheTypes getCacheType(final String cacheType) {
		for (final CacheTypes cacheTypes : CacheTypes.values()) {
			if(cacheTypes.cacheType.equals(cacheType)) {
				return cacheTypes;
			}
		}
		throw new InvalidParameterException();
	}

}
