package com.tant.helper.exporter;

import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tant.helper.loader.JSONLoader;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class JSONExporterFactory {
	private static JSONExporter instance;
	
	public static JSONExporter get() {
		if(Objects.isNull(instance)) {
			// using default
			instance = new JSONExporterImpl(new ObjectMapper());
		}
		return instance;
	}
	
	public static void customMapper(ObjectMapper customMapper) {
		instance = new JSONExporterImpl(customMapper);
	}
}
