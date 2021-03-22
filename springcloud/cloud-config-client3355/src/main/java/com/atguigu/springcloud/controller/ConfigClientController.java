package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RefreshScope
public class ConfigClientController {
    //能不能去配置中心访问到config.info
    @Value("${config.info}")
    private String configinfo;
    @GetMapping("/configinfo/")
    public String getConfigInfo(){
        return configinfo;
    }
}
