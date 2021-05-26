package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 这里是就算消息提供者挂了也不会有影响，会走这里
 * @author bangsun
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService Get Method Fail~";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "PaymentFallbackService TimeOut Method Fail~";
    }
}
