package com.bysj.logistics.manage.controller;

import com.bysj.logistics.manage.pojo.Result;
import com.bysj.logistics.manage.pojo.User;
import com.bysj.logistics.manage.service.UserService;
import com.bysj.logistics.manage.utils.GetModelNameAndType;
import com.bysj.logistics.manage.utils.MD5Util;
import com.bysj.logistics.manage.utils.UUIDToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <br>
 * 登录相关操作
 *
 * @author bangsun
 * @data 2021/4/6 9:38
 * @verson
 */
@RestController
@Api(value = "登录Controller", tags = {"用户登录接口"})
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    public static final ConcurrentHashMap<String, User> MAP = new ConcurrentHashMap<>();

    @Autowired
    private UserService userService;
    @Autowired
    private Map hashMap;

    /**
     * 用户注册
     */
    @ApiOperation(value = "用户注册接口", notes = "注册信息校验与持久化")
    @PostMapping("/registered")
    public Result<User> userRegistered(@RequestBody User user) {

        User res = userService.selectByUserName(user.getUsername());
        if (res != null){
            return Result.succ("该用户已注册！");
        }
        /**默认普通用户*/
        user.setGroupName(1);
        user.setPassword(MD5Util.getPwd(user.getPassword()));
        Integer integer = userService.insertUser(user);
        logger.info("系统新注册用户个数{}，{}，当前时间{}",integer,user,System.currentTimeMillis());
        return Result.succ("注册成功!");
    }

    /**
     * 用户登录,信息放入session
     *
     * @param user
     * @param
     * @return
     */
    @ApiOperation(value = "用户登录接口", notes = "用户登录验证")
    @PostMapping("/login")
    public Result<User> userLogin(@ApiParam(name="user",value="username&password",required=true) @RequestBody User user,
                                        HttpServletRequest request) {

        User nowUser = userService.selectByUserName(user.getUsername());
        if (nowUser == null){
            return Result.fail("该用户不存在");
        }
        String pwd = MD5Util.getPwd(user.getPassword());
        if (pwd.equals(nowUser.getPassword())){
            logger.info("用户{}登录,当前时间{}",nowUser.getUsername(),System.currentTimeMillis());
            request.getSession().setAttribute("username",nowUser);
            MAP.put(nowUser.getUsername(),nowUser);
            Map map = null;
            try {
                map = GetModelNameAndType.reflectToMap(nowUser, hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                return Result.error("-1","To Map Reflect Exception");
            }
            map.put("token", UUIDToken.getToken());

            return Result.succ(map);
        }
        return Result.fail("用户名或密码有误！");
    }

    /**
     * 用户注销登录
     */
    @ApiOperation(value = "用户注销登录接口", notes = "Log Out")
    @GetMapping("/logout")
    public Result<?> logout(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            request.getSession().removeAttribute("user");
            MAP.remove(user.getUsername());
        }
        return Result.succ();
    }
}
