package com.example.jdk8.jdk8demo.service;

import com.example.jdk8.jdk8demo.config.RPCModeConfig;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

@Conditional({RPCModeConfig.DUBBO.class})
@Service
public class DubboService implements RPCService {

    public void test() {
        System.out.println("dubbo");
    }


}
