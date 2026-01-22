package com.example.jdk8.jdk8demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2023/1/9
 * @since 3.0.1
 */
public class MustUnLock {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            //获取本锁保护的资源
            System.out.println(Thread.currentThread().getName());
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

}
