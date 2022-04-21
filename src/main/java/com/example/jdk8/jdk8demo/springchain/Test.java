package com.example.jdk8.jdk8demo.springchain;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
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
public class Test {

    @Autowired
    private List<Init> initList = new ArrayList<>();
    @Autowired
    private InitChainExecutor executor;


    @PostConstruct
    public void initTest() {
        executor.addInitList(initList);
        executor.init(executor);
    }


}
