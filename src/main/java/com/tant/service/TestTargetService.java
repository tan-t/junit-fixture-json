package com.tant.service;

import org.springframework.stereotype.Service;

import com.tant.domain.TestBean;

@Service
public interface TestTargetService {

String sayHello(long id);
}
