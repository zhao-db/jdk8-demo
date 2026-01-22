package com.example.jdk8.jdk8demo.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 自己用AQS实现一个简单的线程协作器
 * </p>
 *
 * @author zhaodb 2023/2/13
 * @since 3.0.1
 */
@Slf4j
public class OneShotLatch {

    private Sync sync = new Sync();

    /**
     * 阻塞
     */
    public void await() {
        sync.acquireShared(0);
    }

    /**
     * 释放
     */
    public void signal() {
        sync.releaseShared(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OneShotLatch oneShotLatch = new OneShotLatch();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                log.info("尝试获取latch,获取失败就等待");
                oneShotLatch.await();
                log.info("开闸放行,继续运行");
            }).start();
        }
        Thread.sleep(5000);
        oneShotLatch.signal();

    }

}
