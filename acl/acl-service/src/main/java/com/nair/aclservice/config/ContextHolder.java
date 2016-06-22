package com.nair.aclservice.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public abstract class ContextHolder {

	private static ApplicationContext context;
	
	public static void initialize(ApplicationContext context){
		ContextHolder.context = context;
		context.getBean("dataSource");
		context.getBean("jdbcTemplate");
	}
	

	public static Object getBean(String name) throws BeansException {
		return context.getBean(name);
	}

	public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return context.getBean(name, requiredType);
	}

	public static <T> T getBean(Class<T> requiredType) throws BeansException {
		return context.getBean(requiredType);
	}

	public static Object getBean(String name, Object... args) throws BeansException {
		return context.getBean(name, args);
	}

	public static <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
		return context.getBean(requiredType, args);
	}
}
