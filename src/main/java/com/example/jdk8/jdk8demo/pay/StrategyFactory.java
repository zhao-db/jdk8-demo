package com.example.jdk8.jdk8demo.pay;

import com.example.jdk8.jdk8demo.enums.PayEnum;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/9/8 15:39
 * @Version 1.0
 */
public class StrategyFactory {


    public static Strategry getStrategy(String strType) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String className = PayEnum.valueOf(strType).getClassName();
        return (Strategry) Class.forName(className).newInstance();
    }

}
