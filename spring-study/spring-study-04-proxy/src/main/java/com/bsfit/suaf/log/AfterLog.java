package com.bsfit.suaf.log;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class AfterLog implements AfterReturningAdvice {
    /**
     * returnResult返回值
     * */
    public void afterReturning(Object returnResult, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println(method.getName()+"方法被执行了，返回了结果"+returnResult);
    }
}
