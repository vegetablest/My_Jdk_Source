package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * mapper
 * @author bangsun
 */
@Mapper
public interface PaymentDao {

    /**
     * 创建订单
     * */
    int create(Payment payment);
    /**
     * 获取订单
     * */
    Payment getPaymentById(@Param("id") Long id);

}
