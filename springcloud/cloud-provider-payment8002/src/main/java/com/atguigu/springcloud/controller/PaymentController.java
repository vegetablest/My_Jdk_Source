package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.serviceImpl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author bangsun
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    PaymentServiceImpl paymentService;

    @Value("${server.port}")
    private String serverPort;
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果：" + result );
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
    @GetMapping("/payment/lb")
    public String paymentLB(){
        return serverPort;
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
}
