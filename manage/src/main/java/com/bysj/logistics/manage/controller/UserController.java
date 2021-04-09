package com.bysj.logistics.manage.controller;

import com.bysj.logistics.manage.pojo.User;
import com.bysj.logistics.manage.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <br>
 *
 * 实现跨域注解
 * origin="*"代表所有域名都可访问
 * maxAge飞行前响应的缓存持续时间的最大年龄，简单来说就是Cookie的有效期 单位为秒
 * 若maxAge是负数,则代表为临时Cookie,不会被持久化,Cookie信息保存在浏览器内存中,浏览器关闭Cookie就消失
 * @author bangsun
 * @data 2021/4/2 22:44
 * @verson
 */
@Controller
@Api(value="用户controller",tags={"用户操作接口"})
@RequestMapping("/user")
public class UserController {

    public static final ConcurrentHashMap<String, User> MAP = new ConcurrentHashMap<>();

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/test")
    public String testStart(@ApiParam(name="id",value="用户id",required=true)@RequestParam("id") Long id,
                            @ApiParam(name="username",value="用户名",required = true) @RequestParam("username") String username){
        System.out.println(id+":"+username);
        return id+":"+username;
    }

}
