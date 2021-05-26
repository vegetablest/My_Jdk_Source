package com.atguigu.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

/**
 * @author bangsun
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixBashBoardMain {
    public static void main(String[] args) {
        SpringApplication.run(HystrixBashBoardMain.class,args);
    }
}
