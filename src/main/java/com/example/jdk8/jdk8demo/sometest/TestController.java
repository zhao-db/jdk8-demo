package com.example.jdk8.jdk8demo.sometest;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jdk8.jdk8demo.service.ServiceTest;
import com.example.jdk8.jdk8demo.service.TestInit;
import com.example.jdk8.jdk8demo.service.ThreadService;
import com.example.jdk8.jdk8demo.testLambda.User;

/**
 * @author Collin
 * @date 2021/4/22 3:00 下午
 */
@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private Map<String, ServiceTest> serviceTestMap;

    @RequestMapping("/test")
    public String sss() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ServiceTest> m : serviceTestMap.entrySet()) {
            System.out.println(m.getValue());
            System.out.println(m.getKey());
            sb.append(m.getKey() + ":" + m.getValue());
        }
        System.out.println("\"sb\" = " + "sb");
        return sb.toString();
    }


    @RequestMapping("/initT")
    public String initS() {
        TestInit testInit = (TestInit) applicationContext.getBean("testInit");
        User user = testInit.getUser();
        user.setPassword("嗯哈");
        return user.toString();
    }

    @Autowired
    private ThreadService threadService;

    @RequestMapping("/testThread")
    public String thread() throws Exception {
        return threadService.testThread();
    }

}
