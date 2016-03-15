/*
 * This File is  the sole property of Paytm(One97 Communications Limited)
 */
package com.nair.cache.common;

import com.nair.cache.expirable.IExpirableCache;

public class TestStringImplementation<K, V> implements IExpirableCache<String, String> {

	@Override
	public String getValue(final String k) {
		if("key1".equals(k)) {
			return "value1";
		}
		if("key2".equals(k)) {
			return "value2";
		}
		return null;
	}

}
