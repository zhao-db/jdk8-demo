package com.example.jdk8.jdk8demo.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 公平锁 线程 123456789
 * 开始排队 第一次打印后释放
 * 第二次打印会按照排队顺序继续获取锁
 * Thread[Thread-0,5,main]开始打印
 * Thread[Thread-0,5,main]正在打印,需要1秒
 * Thread[Thread-1,5,main]开始打印
 * Thread[Thread-2,5,main]开始打印
 * Thread[Thread-3,5,main]开始打印
 * Thread[Thread-4,5,main]开始打印
 * Thread[Thread-5,5,main]开始打印
 * Thread[Thread-6,5,main]开始打印
 * Thread[Thread-7,5,main]开始打印
 * Thread[Thread-8,5,main]开始打印
 * Thread[Thread-9,5,main]开始打印
 * Thread[Thread-1,5,main]正在打印,需要4秒
 * Thread[Thread-2,5,main]正在打印,需要3秒
 * Thread[Thread-3,5,main]正在打印,需要3秒
 * Thread[Thread-4,5,main]正在打印,需要1秒
 * Thread[Thread-5,5,main]正在打印,需要4秒
 * Thread[Thread-6,5,main]正在打印,需要3秒
 * Thread[Thread-7,5,main]正在打印,需要2秒
 * Thread[Thread-8,5,main]正在打印,需要3秒
 * Thread[Thread-9,5,main]正在打印,需要3秒
 * Thread[Thread-0,5,main]正在打印,需要3秒
 * Thread[Thread-0,5,main]打印完成
 * Thread[Thread-1,5,main]正在打印,需要0秒
 * Thread[Thread-1,5,main]打印完成
 * Thread[Thread-2,5,main]正在打印,需要1秒
 * Thread[Thread-2,5,main]打印完成
 * Thread[Thread-3,5,main]正在打印,需要4秒
 * Thread[Thread-3,5,main]打印完成
 * Thread[Thread-4,5,main]正在打印,需要4秒
 * Thread[Thread-4,5,main]打印完成
 * Thread[Thread-5,5,main]正在打印,需要3秒
 * Thread[Thread-5,5,main]打印完成
 * Thread[Thread-6,5,main]正在打印,需要4秒
 * Thread[Thread-6,5,main]打印完成
 * Thread[Thread-7,5,main]正在打印,需要0秒
 * Thread[Thread-7,5,main]打印完成
 * Thread[Thread-8,5,main]正在打印,需要0秒
 * Thread[Thread-8,5,main]打印完成
 * Thread[Thread-9,5,main]正在打印,需要0秒
 * Thread[Thread-9,5,main]打印完成
 * 非公平锁
 * 第一次打印后释放尝试获取锁
 * 会直接由该线程继续持有锁连续执行两次打印
 *
 * tryLock不遵循公平锁规则
 * 公平锁会频繁上下文切换 吞吐量更小
 * 非公平锁可能会有线程饥饿问题
 * Thread[Thread-0,5,main]开始打印
 * Thread[Thread-0,5,main]正在打印,需要3秒
 * Thread[Thread-1,5,main]开始打印
 * Thread[Thread-2,5,main]开始打印
 * Thread[Thread-3,5,main]开始打印
 * Thread[Thread-4,5,main]开始打印
 * Thread[Thread-5,5,main]开始打印
 * Thread[Thread-6,5,main]开始打印
 * Thread[Thread-7,5,main]开始打印
 * Thread[Thread-8,5,main]开始打印
 * Thread[Thread-9,5,main]开始打印
 * Thread[Thread-0,5,main]正在打印,需要3秒
 * Thread[Thread-0,5,main]打印完成
 * Thread[Thread-1,5,main]正在打印,需要1秒
 * Thread[Thread-1,5,main]正在打印,需要9秒
 * Thread[Thread-1,5,main]打印完成
 * Thread[Thread-2,5,main]正在打印,需要8秒
 * Thread[Thread-2,5,main]正在打印,需要8秒
 * Thread[Thread-2,5,main]打印完成
 * Thread[Thread-3,5,main]正在打印,需要3秒
 * Thread[Thread-3,5,main]正在打印,需要0秒
 * Thread[Thread-3,5,main]打印完成
 * Thread[Thread-4,5,main]正在打印,需要9秒
 * Thread[Thread-4,5,main]正在打印,需要2秒
 * Thread[Thread-4,5,main]打印完成
 * Thread[Thread-5,5,main]正在打印,需要4秒
 * Thread[Thread-5,5,main]正在打印,需要2秒
 * Thread[Thread-5,5,main]打印完成
 * Thread[Thread-6,5,main]正在打印,需要3秒
 * Thread[Thread-6,5,main]正在打印,需要9秒
 * Thread[Thread-6,5,main]打印完成
 * Thread[Thread-7,5,main]正在打印,需要0秒
 * Thread[Thread-7,5,main]正在打印,需要0秒
 * Thread[Thread-7,5,main]打印完成
 * Thread[Thread-8,5,main]正在打印,需要0秒
 * Thread[Thread-8,5,main]正在打印,需要2秒
 * Thread[Thread-8,5,main]打印完成
 * Thread[Thread-9,5,main]正在打印,需要3秒
 * Thread[Thread-9,5,main]正在打印,需要0秒
 * Thread[Thread-9,5,main]打印完成
 *
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
    private Lock queueLock = new ReentrantLock(true);

    public void printLog(Object document) {
        queueLock.lock();
        try {
            int duration = new Random().nextInt(5);
            System.out.println(Thread.currentThread() + "正在打印,需要" + duration + "秒");
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            queueLock.unlock();
        }

        queueLock.lock();
        try {
            int duration = new Random().nextInt(5);
            System.out.println(Thread.currentThread() + "正在打印,需要" + duration + "秒");
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            queueLock.unlock();
        }
    }
}
