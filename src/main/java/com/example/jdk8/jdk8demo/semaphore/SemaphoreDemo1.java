package com.example.jdk8.jdk8demo.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2023/1/16
 * @since 3.0.1
 */
@Slf4j
public class SemaphoreDemo1 {
    final static Semaphore semaphore = new Semaphore(3, true);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 100; i++) {
            service.submit(new Task());
        }
        service.shutdown();
    }


    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                semaphore.acquire(3);
                log.info("{}拿到了许可证", Thread.currentThread().getName());
                Thread.sleep((long) (Math.random() * 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(3);
                log.info("{}释放了许可证", Thread.currentThread().getName());
            }
        }
    }

}

