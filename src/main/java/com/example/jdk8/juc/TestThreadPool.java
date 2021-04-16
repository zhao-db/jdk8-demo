package com.example.jdk8.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author zhaodb
 * @Date 2021/3/1 16:18
 * @Version 1.0
 */
@Slf4j
public class TestThreadPool {

    public static ExecutorService createThreadPool() {
        return new ThreadPoolExecutor(2, 3, 1000L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    public static void main(String[] args) {
        ExecutorService service = createThreadPool();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("线程1-----");
            }
        }, "T1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("线程2-----");
            }
        }, "T2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("线程3-----");
            }
        }, "T3");
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("线程4-----");
            }
        }, "T4");
        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("线程5-----");
            }
        }, "T5");
        Thread t6 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("线程6-----");
            }
        }, "T6");
        service.submit(t1);
        service.submit(t2);
        service.submit(t3);
        service.submit(t4);
        service.submit(t5);
        service.submit(t6);

    }

}
