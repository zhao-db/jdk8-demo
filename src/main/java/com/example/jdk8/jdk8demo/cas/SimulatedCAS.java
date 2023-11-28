package com.example.jdk8.jdk8demo.cas;

/**
 * <p>
 *  模拟cas cas等价代码
 * </p>
 *
 * @author zhaodb 2023/11/28
 * @since 3.0.1
 */
public class SimulatedCAS {

    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            this.value = newValue;
        }
        return oldValue;
    }



}
