package com.example.jdk8.jdk8demo.springchain;

import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/4/15
 * @since 3.0.1
 */
@Component
public class WareHouseInit implements Init {

    @Override
    public void init(InitChainExecutor executor) {
        System.out.println("do some WareHouseInit init ");
        executor.init(executor);
    }

    @Override
    public void destroy() {

    }
}
