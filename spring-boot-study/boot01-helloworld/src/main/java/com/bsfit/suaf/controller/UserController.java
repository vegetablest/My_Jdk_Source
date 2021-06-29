package com.bsfit.suaf.controller;

import com.bsfit.suaf.pojo.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/4/25 19:39
 * @verson
 */
@RestController
public class UserController {

    @Autowired
    Car car;
    @GetMapping("/hello")
    public String hello(){
        return "Hello SpringBoot!";
    }

    @GetMapping("/car")
    public Car car(){
        return car;
    }


    @PostConstruct
    public void init(){
        System.out.println("init......................");
    }
}
