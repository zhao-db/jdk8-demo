package com.example.jdk8.jdk8demo.config;

import com.example.jdk8.jdk8demo.service.TestInit;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class BeanConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private TestInit testInit;

    @PostConstruct
    public void init() {
        testInit.init();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
