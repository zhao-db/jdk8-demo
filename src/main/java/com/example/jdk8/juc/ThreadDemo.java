package com.example.jdk8.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/12/15 16:09
 * @Version 1.0
 */
public class ThreadDemo {

    public static ExecutorService createExecutorService() {
        ExecutorService service = new ThreadPoolExecutor(5, 5, 10000,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        return service;
    }

    public static void main(String[] args) {
        ExecutorService service = createExecutorService();
        MyThread myThread = new MyThread("Thread-zdb");
        for (int i = 0; i < 20; i++) {
            service.execute(myThread);
        }
    }

}
