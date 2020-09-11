package com.example.jdk8.jdk8demo;

import com.example.jdk8.jdk8demo.enums.ColorEnum;
import com.example.jdk8.jdk8demo.pay.Strategry;
import com.example.jdk8.jdk8demo.pay.StrategryFactory;

import java.math.BigDecimal;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/9/8 15:15
 * @Version 1.0
 */
public class TestMain {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Strategry str1 = StrategryFactory.getStrategry("ALIPAY");
        str1.pay(BigDecimal.TEN);
        Strategry str2 = StrategryFactory.getStrategry("WECHATPAY");
        str2.pay(BigDecimal.TEN);
        Strategry str3 = StrategryFactory.getStrategry("ICBCPAY");
        str3.pay(BigDecimal.TEN);

    }

}
