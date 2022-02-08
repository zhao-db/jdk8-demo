package com.example.jdk8.jdk8demo;


import com.example.jdk8.jdk8demo.service.RPCService;
import com.example.jdk8.jdk8demo.service.TestInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Jdk8DemoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Jdk8DemoApplication.class, args);
        RPCService bean = applicationContext.getBean(RPCService.class);
        System.out.println(bean.toString());
    }


}
