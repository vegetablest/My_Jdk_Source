package com.atguigu.springcloud.alibaba.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/5/16 16:18
 * @verson
 */
@Component
@FeignClient("nacos-provider")
public interface OrderService {

    @GetMapping(value = "/payment/nacos/{id}")
     String getPayment(@PathVariable("id") Integer id);

}
