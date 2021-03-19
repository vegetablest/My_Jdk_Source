package com.bsfit.suaf.controller;

import com.bsfit.suaf.service.RedisMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/19 9:49
 * @verson
 */
@RestController
public class RedisMQController {

    @Autowired
    private RedisMQService redisMQService;

    @PostMapping("/produce")
    public String produce() {
        String[] names = {"redis message queue", "suaf", "study"};
        for (String name : names) {
            redisMQService.produce(name);
        }
        return "ok";
    }

    @PostMapping("/consume")
    public void consume() {
        int i = 0;
        while (i < 3) {
            redisMQService.consume();
            i++;
        }
    }
}