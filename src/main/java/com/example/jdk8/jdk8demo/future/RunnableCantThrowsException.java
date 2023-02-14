package com.example.jdk8.jdk8demo.future;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * <p>
 * 在run方法中无法抛出checked Exception
 * </p>
 *
 * @author zhaodb 2023/2/13
 * @since 3.0.1
 */
public class RunnableCantThrowsException {

    public static void main(String[] args) {
        Runnable run = () -> {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

}
