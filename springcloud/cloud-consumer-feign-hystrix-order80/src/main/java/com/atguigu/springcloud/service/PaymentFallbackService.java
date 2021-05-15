package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author bangsun
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService失败";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "PaymentFallbackService失败";
    }
}
