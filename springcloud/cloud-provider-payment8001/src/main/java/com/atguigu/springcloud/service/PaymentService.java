package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author bangsun
 */
public interface PaymentService {

    int create(Payment payment);
    Payment getPaymentById(Long id);
}
