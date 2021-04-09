package com.bysj.logistics.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/4/8 23:01
 * @verson
 */
@Configuration
public class ConfigBean {

    @Bean("hashMap")
    public Map<String,Object> hashMap(){
        return new HashMap<>(256);
    }
}
