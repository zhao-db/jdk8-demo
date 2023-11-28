package com.example.jdk8.jdk8demo.lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * 自旋锁
 * </p>
 *
 * @author zhaodb 2023/11/28
 * @since 3.0.1
 */
public class SpinLock {

    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        Thread curr = Thread.currentThread();
        while (!sign.compareAndSet(null, curr)) {
            System.out.println("自旋获取失败，再次尝试" + curr.getName());
        }
    }

    public void unlock() {
        Thread curr = Thread.currentThread();
        sign.compareAndSet(curr, null);
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Runnable r1 = () -> {
            System.out.println("开始尝试获取自旋锁" + Thread.currentThread().getName());
            spinLock.lock();
            System.out.println("获取到了自旋锁" + Thread.currentThread().getName());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
                System.out.println("释放了自旋锁" + Thread.currentThread().getName());
            }
        };
        Runnable r2 = () -> {
            System.out.println("开始尝试获取自旋锁" + Thread.currentThread().getName());
            spinLock.lock();
            System.out.println("获取到了自旋锁" + Thread.currentThread().getName());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
                System.out.println("释放了自旋锁" + Thread.currentThread().getName());
            }
        };
        new Thread(r1, "r1").start();
        new Thread(r2, "r2").start();
    }

}
