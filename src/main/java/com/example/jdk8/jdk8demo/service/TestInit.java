package com.example.jdk8.jdk8demo.service;

import com.example.jdk8.jdk8demo.testLambda.User;
import org.springframework.stereotype.Component;

@Component
public class TestInit {

    public void init() {
        System.out.println("执行init()方法");

    }

    public User getUser() {
        return User.builder().username("zdb").build();
    }

}
