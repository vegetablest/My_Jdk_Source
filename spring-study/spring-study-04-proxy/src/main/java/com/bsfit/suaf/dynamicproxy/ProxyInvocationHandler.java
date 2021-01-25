package com.bsfit.suaf.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理  实现InvocationHandler接口，调用动态代理程序并返回结果的
 * */
public class ProxyInvocationHandler implements InvocationHandler {
    /**
     * 被代理的接口
     * */
    private Object target;
    public void setTarget(Object target) {
        this.target = target;
    }
    /**
     * 生产代理类
     * */
    public Object getProxy(){
        /*
        * Proxy是java.lang.reflect反射下的，该方法就是获得一个代理类
        * 参数：把生产代理类的类加载器给他，目标对象，代理器生产之后给谁
        * */
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
    /**
     * 实现InvocationHandler接口的方法，用invoke方法执行代理对象的方法
     * */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //增强的方法
        printLog(method.getName());
        //执行方法与参数
        Object invoke = method.invoke(target, args);
        return invoke;
    }
    public void printLog(String args){
        System.out.println(String.format("增强的方法，打印执行的方法：%s",args));
    }
}
