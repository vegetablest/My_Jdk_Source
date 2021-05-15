package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bangsun
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feginLoggerLevel(){
        return Logger.Level.FULL;
    }
}
