package com.tant.helper.loader;

import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class JSONLoaderFactory {
	
	private static JSONLoader instance;
	
	public static JSONLoader get() {
		if(Objects.isNull(instance)) {
			// using default
			instance = new JSONLoaderImpl(new ObjectMapper());
		}
		return instance;
	}
	
	public static void customMapper(ObjectMapper customMapper) {
		instance = new JSONLoaderImpl(customMapper);
	}
}
