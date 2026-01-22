package com.example.jdk8.jdk8demo.cas;

import lombok.SneakyThrows;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2023/11/28
 * @since 3.0.1
 */
public class TwoThreadCompetition implements Runnable {


    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            this.value = newValue;
        }
        return oldValue;
    }

    @Override
    public void run() {
        compareAndSwap(0, 1);
    }

    @SneakyThrows
    public static void main(String[] args) {
        TwoThreadCompetition r = new TwoThreadCompetition();
        Thread t1 = new Thread(r, "thread-1");
        Thread t2 = new Thread(r, "thread-2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }

}
