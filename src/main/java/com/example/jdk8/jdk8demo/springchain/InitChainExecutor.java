package com.example.jdk8.jdk8demo.springchain;

import java.util.ArrayList;
import java.util.List;

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
public class InitChainExecutor implements Init {

    private static final List<Init> initChain = new ArrayList<>();

    private int index = 0;

    public void addInit(Init init) {
        initChain.add(init);
    }

    public void addInitList(List<Init> init) {
        initChain.addAll(init);
    }

    @Override
    public void init(InitChainExecutor executor) {
        System.out.println("executor init ");
        if (index == initChain.size()) {
            return;
        }
        Init initChain = InitChainExecutor.initChain.get(index);
        index++;
        initChain.init(executor);
    }

    @Override
    public void destroy() {

    }
}
