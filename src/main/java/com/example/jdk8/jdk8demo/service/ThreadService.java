package com.example.jdk8.jdk8demo.service;

import com.example.jdk8.jdk8demo.testLambda.MyThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class ThreadService {

    @Autowired
    private MyThread myThread;

    public String testThread() throws Exception {
        List<Future<String>> futureList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            System.out.println("开始循环");
            Future<String> call = myThread.call();
            futureList.add(call);
            System.out.println("结束循环");
        }
        System.out.println("futureList = " + futureList);
        for (Future<String> future : futureList) {
            System.out.println("future.get() = " + future.get());
        }
        System.out.println("12321313123");
        return "";
    }

}
