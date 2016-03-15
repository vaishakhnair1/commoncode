/*
 * This File is  the sole property of Paytm(One97 Communications Limited)
 */
package com.nair.cache.common;

import com.nair.cache.expirable.IExpirableCache;

public class TestIntegerImplementation<K, V> implements IExpirableCache<Integer, Integer> {

	@Override
	public Integer getValue(final Integer k) {
		return k.equals(1) ? 1 : k.equals(2) ? 2 :0;
	}

}
