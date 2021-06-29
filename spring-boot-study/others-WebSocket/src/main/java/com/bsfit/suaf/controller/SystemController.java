package com.bsfit.suaf.controller;

import com.bsfit.suaf.webSocket.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/5/8 9:46
 * @verson
 */
@RestController("web_Scoket_system")
@RequestMapping("/api/socket")
public class SystemController {
    /**
     * 页面请求
     */
    @GetMapping("/index/{userId}")
    public ModelAndView socket(@PathVariable String userId) {
        ModelAndView mav = new ModelAndView("/socket1");
        mav.addObject("userId", userId);
        return mav;
    }

    /**
     * 推送数据接口
     */
    @RequestMapping("/socket/push/{cid}")
    public Map pushToWeb(@PathVariable String cid, String message) {
        Map<String,Object> result = new HashMap<>(16);
        try {
            WebSocketServer.sendInfo(message, cid);
            result.put("code", cid);
            result.put("msg", message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
