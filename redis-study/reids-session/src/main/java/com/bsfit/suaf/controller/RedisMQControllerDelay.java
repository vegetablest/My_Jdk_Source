package com.bsfit.suaf.controller;

import com.bsfit.suaf.prodiver.MessageProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/19 10:11
 * @verson
 */
@RestController
public class RedisMQControllerDelay {
    @Resource
    private MessageProvider messageProvider;

    @PostMapping("/delay/produce")
    public String produce() {
        //延迟20秒
        messageProvider.sendMessage("同时发送消息1", 20);
        messageProvider.sendMessage("同时发送消息2", 20);
        return "ok";
    }
}
