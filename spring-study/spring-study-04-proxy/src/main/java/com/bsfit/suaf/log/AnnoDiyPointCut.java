package com.bsfit.suaf.log;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author bangsun
 */
//标注该类是一个切面

@Aspect
public class AnnoDiyPointCut {
    @Before("execution(* com.bsfit.suaf.staticproxy.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("注解织入方法执行前");
    }
    @After("execution(* com.bsfit.suaf.staticproxy.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("注解织入方法执行后");
    }
}
