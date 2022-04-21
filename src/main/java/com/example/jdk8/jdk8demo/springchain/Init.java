package com.example.jdk8.jdk8demo.springchain;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/4/21
 * @since 3.0.1
 */
public interface Init {

    void init(InitChainExecutor executor);

    void destroy();

}
