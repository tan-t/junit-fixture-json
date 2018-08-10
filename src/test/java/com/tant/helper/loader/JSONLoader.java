package com.tant.helper.loader;

import java.util.List;

public interface JSONLoader {
	public <T> T load(Class<T> beanClazz,Class<?> testClass,String path); 
	List<Object[]> data(Class<?>[] beanClasses, Class<?> testClass, String path);
	List<Object[]> parameters(Class<?> testClass, String path);
}
