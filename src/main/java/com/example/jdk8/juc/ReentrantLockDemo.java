package com.example.jdk8.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/12/24 10:16
 * @Version 1.0
 */
public class ReentrantLockDemo implements Runnable {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    private static CountDownLatch c1 = new CountDownLatch(10);

    private volatile int i = 0;

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();
        t2.start();
        c1.await();
        System.out.println("123");
    }


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        do {
            i++;
            System.out.println(i);
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "get Lock");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
                c1.countDown();
            }
        } while (i < 10);
    }
}
