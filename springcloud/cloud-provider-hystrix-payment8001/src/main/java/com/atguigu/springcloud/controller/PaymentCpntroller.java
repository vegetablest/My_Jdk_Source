package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author bangsun
 */
@RestController
@Slf4j
public class PaymentCpntroller {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id")Integer id){
        String result = paymentService.paymentInfo(id);
        log.info(result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id")Integer id){
        String result = paymentService.paymentInfo_Timeout(id);
        log.info(result);
        return result;
    }
    @GetMapping("/payment/hystrix/circuit/{id}")
    public String circuit(@PathVariable("id")Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        return result;
    }

}