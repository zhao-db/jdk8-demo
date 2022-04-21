package com.example.jdk8.jdk8demo.springchain;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/4/15
 * @since 3.0.1
 */
public abstract class InitChain {

    protected InitChain nextChain;

    public abstract void init();


}
