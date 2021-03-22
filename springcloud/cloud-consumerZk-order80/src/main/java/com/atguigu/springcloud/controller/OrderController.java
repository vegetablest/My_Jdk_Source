package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@RestController
public class OrderController {

    @Resource
    private RestTemplate restTemplate;
    private static final String INVOKE_URL="http://cloud-payment-service" ;

    @GetMapping("/consumer/payment/zk")
    public String paymentinfo(){

        String result = restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
        return  result;
    }



}
