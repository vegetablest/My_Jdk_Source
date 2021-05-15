package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.serviceImpl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author bangsun
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    PaymentServiceImpl paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("查询结果：" + result );
        if (result >0){
            return new CommonResult(200,"插入成功,端口号：" + serverPort,result);

        }else {
            return new CommonResult(444,"插入失败,端口号："+ serverPort,null);
        }
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果：" + payment );
        if (payment != null){
            return new CommonResult(200,"查询成功,端口号：" + serverPort,payment);

        }else {
            return new CommonResult(444,"查询失败,根据id:" + id,null);
        }
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
    @GetMapping("/payment/lb")
    public String paymentLB(){
        return serverPort;
    }


    /**
     * 服务发现，看看注册进服务注册中心的服务信息
     * */
    @GetMapping("/payment/discovery")
    public Object getDiscoveryClient(){
        List<String> services = discoveryClient.getServices();
        log.info("服务清单列表:"+services);
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(instance -> log.info("服务实例Id:"+instance.getInstanceId()+"服务实例host:"+instance.getHost()+",服务实例端口:"+instance.getPort()));
        return new CommonResult<>(200,"查询结果",instances);
    }

}
