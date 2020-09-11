package com.example.jdk8.jdk8demo.pay;

import java.math.BigDecimal;

public interface Strategry {

    /**
     * 计算支付金额，通过商品、渠道ID 调用各自银行银行支付
     *
     * @param channelId
     * @param goodsId
     * @return
     */
    BigDecimal calRecharge(Integer channelId, Integer goodsId);

    /**
     * 支付
     *
     * @param money
     */
    void pay(BigDecimal money);

}
