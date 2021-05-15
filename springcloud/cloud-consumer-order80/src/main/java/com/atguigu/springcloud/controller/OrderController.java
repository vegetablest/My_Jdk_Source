package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * 80消费者组件
 * @author bangsun
 */
@RestController
@Slf4j
public class OrderController {

    /**restTemplate调用*/
    /*private static final String PAYMENT_URL="http://localhost:8001";*/

    /**eureka集群时url不能写死,使用eureka中用的服务名*/
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancer loadBalancer;

    /**
     * postForObject返回json字符串
     * */
    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }


    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    /**
     * getForEntity返回ResponseEntity，含有消息头，消息体的实体类
     * */
    @GetMapping("/consumer/getForEntity/create")
    public CommonResult create1(Payment payment) {
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult(444,"错误");
        }
    }

    @GetMapping("/consumer/getForEntity/get/{id}")
    public CommonResult<Payment> getPayment1(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(444,"错误");
        }
    }

    @GetMapping("/payment/lb")
    public String paymentLB(){

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() < 0 ){
            return null;
        }
        ServiceInstance instance = loadBalancer.getInstances(instances);
        URI uri = instance.getUri();
        return  restTemplate.getForObject(uri+"/payment/lb",String.class);
    }
}
