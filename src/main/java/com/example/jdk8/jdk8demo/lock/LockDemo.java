package com.example.jdk8.jdk8demo.lock;

import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2023/2/13
 * @since 3.0.1
 */
@Slf4j
public class LockDemo {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                log.info("t1 get Lock and do something");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                log.info("t1 release Lock ");
            }

        });
        Thread t2 = new Thread(() -> {
            try {
                log.info("t2 un Lock ");
                lock.unlock();
                lock.lock();
                log.info("t2 get Lock and do something");
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                log.info("t2 release Lock ");
            }
        });
        t1.start();
        t2.start();
        t1.join();
        log.info("t1 execute end..");
        t2.join();
        log.info("t2 execute end..");
        log.info("execute end..");

    }

}
