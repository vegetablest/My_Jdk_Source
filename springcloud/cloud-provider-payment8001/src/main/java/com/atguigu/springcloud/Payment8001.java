package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * EurekaClient     Eureka客户端
 * DiscoveryClient 服务发现客户端
 * @author bangsun
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class Payment8001 {
    public static void main(String[] args) {
        SpringApplication.run(Payment8001.class,args);
    }
}
