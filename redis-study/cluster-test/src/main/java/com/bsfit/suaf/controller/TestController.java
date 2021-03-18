package com.bsfit.suaf.controller;

import com.bsfit.suaf.mycomponent.User;
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
 * @data 2021/3/12 16:08
 * @verson
 */
@Slf4j
@CrossOrigin
@RestController()
public class TestController {

    @Autowired
    @Qualifier("stringRedisTemplate2")
    RedisTemplate<String,String> stringRedisTemplate2;

    @Autowired
    User user;

    @RequestMapping("/test")
    public String hello(){
        System.out.println("欢迎使用SpringBoot");
        System.out.println(user);
//        stringRedisTemplate2.opsForValue().set("name","汉字");
//        String name = stringRedisTemplate2.opsForValue().get("name");
//        System.out.println("name = " + name);
//        System.out.println("name size=" + name.length());
        return "欢迎使用SpringBoot";
    }
}