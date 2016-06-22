package com.nair.aclservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JsonMapper {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	public static String getJsonFromObject(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
