package com.example.jdk8.juc;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/12/24 15:31
 * @Version 1.0
 */
public class CyclicBarrierTest implements Runnable {

    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierTest(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
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
            Random rn = new Random();
            int rand = rn.nextInt(3000);
            Thread.sleep(rand);
            System.out.println(Thread.currentThread().getName() + "，线程执行完毕，用时" + (double) rand / 1000 + "s");
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName()+"结束");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
