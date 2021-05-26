package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/5/16 19:47
 * @verson
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelServerMain {
    public static void main(String[] args) {
        SpringApplication.run(SentinelServerMain.class,args);
    }
}
