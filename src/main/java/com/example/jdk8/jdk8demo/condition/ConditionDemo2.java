package com.example.jdk8.jdk8demo.condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 用condition实现生产者消费者模式
 * </p>
 *
 * @author zhaodb 2023/1/16
 * @since 3.0.1
 */
@Slf4j
public class ConditionDemo2 {

    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        ConditionDemo2 demo2 = new ConditionDemo2();
        Consumer consumer = demo2.new Consumer();
        Producer producer = demo2.new Producer();
        producer.start();
        consumer.start();
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        log.info("队列空，等待数据");
                        notEmpty.await();
                    }
                    queue.poll();
                    notFull.signal();
                    log.info("从队列里取走了一个数据，队列剩余：{}个元素", queue.size());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

    }

    class Producer extends Thread {
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == queueSize) {
                        log.info("队列满，等待有空余");
                        notFull.await();
                    }
                    queue.offer(1);
                    notEmpty.signal();
                    log.info("向队列里插入了一个数据，队列剩余空间：{}", queueSize - queue.size());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

    }

}
