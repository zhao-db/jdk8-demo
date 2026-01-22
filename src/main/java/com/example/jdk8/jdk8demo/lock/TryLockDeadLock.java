package com.example.jdk8.jdk8demo.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * tryLock避免死锁
 * </p>
 *
 * @author zhaodb 2023/1/9
 * @since 3.0.1
 */
@Slf4j
public class TryLockDeadLock implements Runnable {

    int flag = 1;
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (flag == 1) {
                try {
                    if (lock1.tryLock(3000, TimeUnit.MILLISECONDS)) {
                        try {
                            Thread.sleep(new Random().nextInt(1000));
                            log.info(Thread.currentThread().getName() + "获取锁1成功 ");
                            if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                                try {
                                    log.info(Thread.currentThread().getName() + "获取锁2成功 ");
                                    log.info(Thread.currentThread().getName() + "成功获取两把锁 ");
                                    break;
                                } finally {
                                    lock2.unlock();
                                    log.info("锁2释放");
                                    Thread.sleep(new Random().nextInt(1000));
                                }
                            } else {
                                log.info(Thread.currentThread().getName() + "获取锁2 失败 已重试");
                            }
                        } finally {
                            lock1.unlock();
                            log.info("锁1释放");
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        log.info(Thread.currentThread().getName() + "获取锁1 失败 已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (flag == 0) {
                try {
                    if (lock2.tryLock(3000, TimeUnit.MILLISECONDS)) {
                        try {
                            log.info(Thread.currentThread().getName() + "获取锁2成功 ");
                            Thread.sleep(new Random().nextInt(1000));
                            if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                                try {
                                    log.info(Thread.currentThread().getName() + "获取锁1成功 ");
                                    log.info(Thread.currentThread().getName() + "成功获取两把锁 ");
                                    break;
                                } finally {
                                    lock1.unlock();
                                    log.info("锁1释放");
                                    Thread.sleep(new Random().nextInt(1000));
                                }
                            } else {
                                log.info(Thread.currentThread().getName() + "获取锁1 失败 已重试");
                            }
                        } finally {
                            lock2.unlock();
                            log.info("锁2释放");
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        log.info(Thread.currentThread().getName() + "获取锁2 失败 已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        TryLockDeadLock r1 = new TryLockDeadLock();
        TryLockDeadLock r2 = new TryLockDeadLock();
        r1.flag = 1;
        r2.flag = 0;
        new Thread(r1, "线程1").start();
        new Thread(r2, "线程2").start();
    }

}
