package com.tant.helper.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JSONLoaderImpl implements JSONLoader {
	
	private final ObjectMapper om;

	@Override
	public <T> T load(Class<T> beanClazz,Class<?> testClass,String path){
		try {
			return this.om.readValue(new File(testClass.getResource(path).toURI()), beanClazz);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public List<Object[]> data(Class<?>[] beanClasses, Class<?> testClass, String path) {
		try {
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> maps = this.om.readValue(new File(testClass.getResource(path).toURI()), List.class);
			return maps.stream().map(map->datum(beanClasses,map)).collect(Collectors.toList());
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Object[]> parameters(Class<?> testClass, String path) {
		return this.data(testClass.getDeclaredConstructors()[0].getParameterTypes(),testClass,path);
	}
	
	private Object[] datum(Class<?>[] beanClasses,Map<String,Object> map) {
		List<Object> arr = new ArrayList<Object>();
		int i = 0;
		for(Entry<String,Object> e: map.entrySet()) {
			arr.add(om.convertValue(e.getValue(), beanClasses[i]));
			i++;
		}
		return arr.toArray();
	}
	
	private String loadResource(Class<?> clazz,String pathStr) throws URISyntaxException, IOException {
		Path path = Paths.get(clazz.getResource(pathStr).toURI());
		BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		return br.lines().collect(Collectors.joining());
	}
  
}
