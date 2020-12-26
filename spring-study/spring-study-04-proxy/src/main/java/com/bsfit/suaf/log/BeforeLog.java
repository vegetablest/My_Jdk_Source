package com.bsfit.suaf.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 前置增强
 * @author bangsun
 */
public class BeforeLog implements MethodBeforeAdvice {
    /**
     * method  要执行的方法
     * objects 参数
     * o       目标对象
     * */
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println(o.getClass().getSimpleName()+"的"+method.getName()+"方法被执行了");
    }

}
