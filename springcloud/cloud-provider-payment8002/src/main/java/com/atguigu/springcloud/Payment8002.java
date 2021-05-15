package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 生产提供者8002
 * @author bangsun
 */
@EnableEurekaClient
@SpringBootApplication
public class Payment8002 {
    public static void main(String[] args) {
        SpringApplication.run(Payment8002.class, args);
    }
}
