package com.bsfit.suaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/21 20:24
 * @verson
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
