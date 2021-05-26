package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/5/16 19:49
 * @verson
 */
@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handException")
    public CommonResult<String> byResource(){

        return new CommonResult<String>(200,"通过");
    }

    public CommonResult<String> handException(){

        return new CommonResult<String>(400,"SentinelHostKey Policy");
    }

    @GetMapping("/byResource/url")
    @SentinelResource(value = "url")
    public CommonResult<String> byResourceUrl(){

        return new CommonResult<String>(200,"按url进行流控");
    }
}
