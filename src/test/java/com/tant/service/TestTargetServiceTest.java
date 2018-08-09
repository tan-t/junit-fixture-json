package com.tant.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tant.dao.TestDao;
import com.tant.domain.PetBean;
import com.tant.domain.TestBean;
import com.tant.helper.annotation.Fixture;
import com.tant.helper.loader.JSONLoader;
import com.tant.helper.loader.JSONLoaderImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RunWith(Parameterized.class)
@Slf4j
@RequiredArgsConstructor
public class TestTargetServiceTest {

	@Mock
	private TestDao testDao;

	@InjectMocks
	private TestTargetService service = new TestTargetServiceImpl();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	private final TestBean person;
	private final PetBean pet;
	private final String expectation;
	private final String testCaseName;
	
	@Parameters(name="{3}")
	public static List<Object[]> data(){
		return new JSONLoaderImpl(new ObjectMapper()).parameters(TestTargetServiceTest.class, "./data.json");
	}
	
	@Test
	public void test() {
		when(testDao.getTestBeanById(0)).thenReturn(this.person);
		assertThat(service.sayHello(0),is(this.expectation));
	}
}
