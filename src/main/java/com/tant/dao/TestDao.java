package com.tant.dao;

import org.springframework.stereotype.Repository;

import com.tant.domain.TestBean;

@Repository
public interface TestDao {
  public TestBean getTestBeanById(long id);
}
