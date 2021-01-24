package com.bsfit.suaf.pojo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloCircularDependency {

    @Test
    public void testDependency(){
        //获取spring的上下文对对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans3.xml");
        //对象交给spring的上下文管理了
        A a =  applicationContext.getBean("a",A.class);
        B b =  applicationContext.getBean("b",B.class);
        System.out.println("A===>"+a+"\n"+"B===>"+b);
    }

    @Test
    public void testDependency01(){
        A a = new A();
        B b = new B();
        a.setB(b);
        b.setA(a);
    }
}
