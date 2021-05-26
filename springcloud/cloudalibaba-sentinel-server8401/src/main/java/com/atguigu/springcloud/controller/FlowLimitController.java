package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "----------A";
    }

    @GetMapping("/testB")
    public String testB(){
        return "----------B";
    }
    @GetMapping("/testC")
    public String testC() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return "----------C";
    }
    @GetMapping("/testD")
    public String testD(){
        return "----------D";
    }
    @GetMapping("/testE/{id}")
    public String testE(@PathVariable("id")Integer id){
        if (id < 0){
            throw new RuntimeException("不能是复数");
        }
        return "----------E";
    }

    @GetMapping("/testHostKey")
    @SentinelResource(value = "testHostKey",blockHandler = "deal_testHostKey")
    public String  testHostKey(@RequestParam(value = "p1",required = false)String p1,
                               @RequestParam(value = "p2",required = false)String p2){

        return "热点key";
    }


    public String  deal_testHostKey(String p1, String p2, BlockException blockException){

        return "SentinelHostKey Policy";
    }
}
