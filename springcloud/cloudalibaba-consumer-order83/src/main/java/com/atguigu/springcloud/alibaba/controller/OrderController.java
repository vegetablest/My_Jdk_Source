package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author bangsun
 */
@Slf4j
@RestController
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @Autowired
    private OrderService orderService;
    @GetMapping(value = "/consumer/payment/nacos/{id}")
    private String paymentInfo(@PathVariable("id") Integer id) {
//        return restTemplate.getForObject(serverURL + "/payment/nacos/" + id, String.class);
        return orderService.getPayment(id);
    }

}
