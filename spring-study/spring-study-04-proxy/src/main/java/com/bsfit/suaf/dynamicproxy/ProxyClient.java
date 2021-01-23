package com.bsfit.suaf.dynamicproxy;

import com.bsfit.suaf.staticproxy.UserService;
import com.bsfit.suaf.staticproxy.UserServiceImpl;

/**
 * @author bangsun
 */
public class ProxyClient {


    public static void main(String[] args) {

        //目标对象
        UserServiceImpl userService = new UserServiceImpl();
        //代理角色，不存在
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        //设置代理的对象
        proxyInvocationHandler.setTarget(userService);
        //生产动态代理类的接口
        UserService proxy = (UserService) proxyInvocationHandler.getProxy();
        //jdk创建的动态代理对象proxy:com.sun.proxy.$Proxy0
        System.out.println("proxy:"+proxy.getClass().getName());
        //执行方法
        proxy.add();
        proxy.delete();
        proxy.update();
        proxy.query();

    }
}
