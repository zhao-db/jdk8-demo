package com.example.jdk8.jdk8demo.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * condition 基本用法
 * </p>
 *
 * @author zhaodb 2023/1/16
 * @since 3.0.1
 */
@Slf4j
public class ConditionDemo1 {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void method1() {
        lock.lock();
        try {
            log.info("条件不满足开始await");
            condition.await();
            log.info("条件满足了，开始执行后续任务");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void method2() {
        lock.lock();
        try {
            log.info("准备工作完成，开始唤醒其他的线程");
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo1 conditionDemo1 = new ConditionDemo1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                conditionDemo1.method2();
            }
        }).start();
        conditionDemo1.method1();

    }

}
