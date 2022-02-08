package com.example.jdk8.jdk8demo.service;

import com.example.jdk8.jdk8demo.config.RPCModeConfig;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

@Conditional({RPCModeConfig.MOTAN.class})
@Service
public class MotanService implements RPCService {

    public void test() {
        System.out.println("motan");
    }


}
