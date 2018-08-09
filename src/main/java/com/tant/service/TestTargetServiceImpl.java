package com.tant.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tant.dao.TestDao;
import com.tant.domain.TestBean;

public class TestTargetServiceImpl implements TestTargetService{

	@Autowired
	private TestDao testDao;
	
	@Override
	public String sayHello(long id) {
		return "hello," + testDao.getTestBeanById(id).getName();
	}

}
