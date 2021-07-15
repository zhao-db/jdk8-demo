package com.example.jdk8.jdk8demo.service;

/**
 * @author Collin
 * @date 2021/4/22 2:56 下午
 */
@org.springframework.stereotype.Service("serviceA")
public class TestServiceTestA implements ServiceTest {
    @Override
    public String sayHello() {
        return "helloA";
    }
}
