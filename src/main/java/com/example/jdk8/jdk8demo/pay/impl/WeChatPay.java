package com.example.jdk8.jdk8demo.pay.impl;

import com.example.jdk8.jdk8demo.pay.Strategry;

import java.math.BigDecimal;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/9/7 11:18
 * @Version 1.0
 */
public class WeChatPay implements Strategry {


    /**
     * 计算支付金额，通过商品、渠道ID 调用各自银行银行支付
     *
     * @param channelId
     * @param goodsId
     * @return
     */
    @Override
    public BigDecimal calRecharge(Integer channelId, Integer goodsId) {
        return null;
    }

    /**
     * 银行支付
     *
     * @param money
     */
    @Override
    public void pay(BigDecimal money) {
        System.out.println("WeChat支付");
    }
}
