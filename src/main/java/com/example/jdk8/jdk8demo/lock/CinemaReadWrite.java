package com.example.jdk8.jdk8demo.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>
 * 共享锁和排他锁
 *
 * </p>
 *
 * @author zhaodb 2024/7/16
 * @since 3.0.1
 */
public class CinemaReadWrite {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();


    private static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 得到了读锁，正在读取");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(Thread.currentThread().getName() + " 释放了读锁");
            readLock.unlock();
        }
    }

    private static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 得到了写锁，正在写入");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(Thread.currentThread().getName() + " 释放了写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            read();
        }, "Thread1").start();
        new Thread(() -> {
            read();
        }, "Thread2").start();
        new Thread(() -> {
            write();
        }, "Thread3").start();
        new Thread(() -> {
            read();
        }, "Thread5").start();
        new Thread(() -> {
            write();
        }, "Thread4").start();


    }


}
