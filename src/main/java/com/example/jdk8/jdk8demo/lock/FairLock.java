package com.example.jdk8.jdk8demo.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 公平锁 线程 123456789
 * 开始排队 第一次打印后释放
 * 第二次打印会按照排队顺序继续获取锁
 * 非公平锁
 * 第一次打印后释放尝试获取锁
 * 会直接由该线程继续持有锁连续执行两次打印
 *
 * tryLock不遵循公平锁规则
 * </p>
 *
 * @author zhaodb 2024/7/16
 * @since 3.0.1
 */
public class FairLock {
    public static void main(String[] args) throws InterruptedException {
        PrintQueue printQueue = new PrintQueue();
        Thread[] thread = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue));
        }
        for (int i = 0; i < 10; i++) {
            thread[i].start();
            Thread.sleep(100);
        }
    }

}

class Job implements Runnable {
    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    PrintQueue printQueue;

    @Override
    public void run() {
        System.out.println(Thread.currentThread() + "开始打印");
        printQueue.printLog(new Object());
        System.out.println(Thread.currentThread() + "打印完成");
    }
}

class PrintQueue {
    private Lock queueLock = new ReentrantLock(false);

    public void printLog(Object document) {
        queueLock.lock();
        try {
            int duration = new Random().nextInt(10);
            System.out.println(Thread.currentThread() + "正在打印,需要" + duration + "秒");
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            queueLock.unlock();
        }

        queueLock.lock();
        try {
            int duration = new Random().nextInt(10);
            System.out.println(Thread.currentThread() + "正在打印,需要" + duration + "秒");
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            queueLock.unlock();
        }
    }
}
