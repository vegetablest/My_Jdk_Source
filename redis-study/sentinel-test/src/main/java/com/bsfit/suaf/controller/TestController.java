package com.bsfit.suaf.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/16 20:22
 * @verson
 */
@Slf4j
@CrossOrigin
@RestController()
public class TestController {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @RequestMapping("/test")
    public String hello(){
        System.out.println("欢迎使用SpringBoot");
        redisTemplate.opsForValue().set("name","zhangsanfeng");
        String name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
        System.out.println("name size=" + name.length());
        return "欢迎使用SpringBoot";
    }
}
