package com.bsfit.suaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/12 16:06
 * @verson
 */
@SpringBootApplication
@EnableCaching
@CrossOrigin
public class RedisApp {
    public static void main(String[] args) {
        SpringApplication.run(RedisApp.class,args);
    }
}
