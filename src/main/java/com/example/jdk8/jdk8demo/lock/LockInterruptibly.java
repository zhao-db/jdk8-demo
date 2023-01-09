package com.example.jdk8.jdk8demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2023/1/9
 * @since 3.0.1
 */
@Slf4j
public class LockInterruptibly implements Runnable {

    private static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        log.info("尝试获取锁");
        try {
            lock.lockInterruptibly();
            try {
                log.info("获取到了锁");
                Thread.sleep(5000);
            } catch (Exception e) {
                log.error("睡眠期间被中断");
            } finally {
                lock.unlock();
                log.info("释放了锁");
            }
        } catch (InterruptedException e) {
            log.error("等锁期间被中断了");
        }

    }

    public static void main(String[] args) throws InterruptedException {
        LockInterruptibly r1 = new LockInterruptibly();
        LockInterruptibly r2 = new LockInterruptibly();
        Thread t1 = new Thread(r1, "线程1");
        t1.start();
        Thread t2 = new Thread(r2, "线程2");
        t2.start();

        Thread.sleep(200);
        t2.interrupt();

    }

}
