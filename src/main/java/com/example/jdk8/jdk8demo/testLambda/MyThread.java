package com.example.jdk8.jdk8demo.testLambda;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class MyThread {

    @Async("taskExecutor")
    public Future<String> call() throws Exception {
        System.out.println("执行线程操作====" + Thread.currentThread().getName());
        System.out.println("先休息2秒====");
        Thread.sleep(2000);
        System.out.println("休息结束====" + Thread.currentThread().getName());
        return new AsyncResult<>(Thread.currentThread().getName());
    }
}
