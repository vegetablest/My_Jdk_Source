package com.atguigu.springcloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 必须加这个注解，开启服务发现
 * @author bangsun
 */
@SpringBootApplication
public class Payment8004 {
    public static void main(String[] args) {
        SpringApplication.run(Payment8004.class,args);
    }
}
