package com.bsfit.suaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <br>
 * redis的session共享基于过滤器
 * @author bangsun
 * @data 2021/3/17 21:05
 * @verson
 */

@Controller
@RequestMapping("test")
public class TestSession {
    @RequestMapping("test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**这个其实是从redis取到的*/
        List<String> list = (List<String>) request.getSession().getAttribute("list");

        if (list == null){
            list = new ArrayList<>();
        }
        list.add("xxx");
        /**但是这里有个小细节，这里需要放回redis同步到redis中，每次session变化都要同步session*/
        request.getSession().setAttribute("list",list);
        response.getWriter().println("size:"+list.size());
        response.getWriter().println("sessionId:"+request.getSession().getId());
    }
    @RequestMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /**退出，删除redis*/
        request.getSession().invalidate();
    }
}
