package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@RestController
public class OrderController {
    @Resource
    private RestTemplate restTemplate;
    private static final String INVOKE_URL="http://consul-provider-payment" ;

    @GetMapping("/consumer/payment/consul")
    public String paymentinfo(){

        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        return  result;
    }
}
