package com.example.jdk8.juc;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/12/24 15:14
 * @Version 1.0
 */
public class TestCountDownLatch implements Runnable {

    private CountDownLatch countDownLatch;

    public TestCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
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
        try {
            Random random = new Random();
            int rand = random.nextInt(3000);
            Thread.sleep(rand);
            System.out.println(Thread.currentThread().getName() + "已经准备好了，所使用时间" + (double) rand / 1000 + "s");
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
