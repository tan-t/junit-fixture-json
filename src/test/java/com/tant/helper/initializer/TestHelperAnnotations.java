package com.tant.helper.initializer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

import com.tant.helper.annotation.Fixture;

public class TestHelperAnnotations {
	
	public static void initFixtures(Object testObject) {
		Class<?> clazz = testObject.getClass();
		Arrays.stream(clazz.getDeclaredFields()).filter(f->Objects.nonNull(f.getDeclaredAnnotation(Fixture.class))).forEach(f->{
			loadFixture(testObject,f,f.getDeclaredAnnotation(Fixture.class));
		});
	}
	
	private static <T> void loadFixture(T testObject,Field field,Fixture annotation) {
		
	}
}
