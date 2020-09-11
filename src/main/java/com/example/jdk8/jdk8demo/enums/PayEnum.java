package com.example.jdk8.jdk8demo.enums;

import com.example.jdk8.jdk8demo.pay.impl.AliPay;
import com.example.jdk8.jdk8demo.pay.impl.ICBCPay;
import lombok.Getter;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/9/8 15:36
 * @Version 1.0
 */
@Getter
public enum PayEnum {

    ALIPAY("com.example.jdk8.jdk8demo.pay.impl.AliPay"),

    WECHATPAY("com.example.jdk8.jdk8demo.pay.impl.WeChatPay"),

    ICBCPAY("com.example.jdk8.jdk8demo.pay.impl.ICBCPay");

    private String className;

    PayEnum(String className) {
        this.className = className;
    }

}
