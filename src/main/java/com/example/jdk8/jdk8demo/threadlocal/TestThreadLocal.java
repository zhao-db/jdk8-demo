package com.example.jdk8.jdk8demo.threadlocal;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/2/16
 * @since 3.0.1
 */
public class TestThreadLocal {

    public static ExecutorService getExecutorService() {
        ExecutorService executorService = new ThreadPoolExecutor(10, 10, 1000,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
        return executorService;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            startThread();
        }
    }

    public static void startThread() {
        getExecutorService().submit(() -> {
            boolean flag = true;
            int i = 0;
            while (flag) {
                i++;
                new Service1().process(i);
                if (i > 5) {
                    flag = false;
                }
            }
        });
    }


    static class UserContextHolder {
        public static final ThreadLocal<User> holder = new ThreadLocal();
    }

}
