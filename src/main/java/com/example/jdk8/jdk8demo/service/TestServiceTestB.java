package com.example.jdk8.jdk8demo.service;

import org.springframework.stereotype.Service;

/**
 * @author Collin
 * @date 2021/4/22 2:56 下午
 */
@Service("serviceB")
public class TestServiceTestB implements ServiceTest {
    @Override
    public String sayHello() {
        return "helloB";
    }
}
