package com.bsfit.suaf.test;

import com.bsfit.suaf.staticproxy.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.misc.ProxyGenerator;

import java.io.*;

public class TestAop {

    @Test
    public void testAop01(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.add();
    }

    @Test
    public void testAop02(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.add();
    }

    @Test
    public void testProxy() throws IOException {
        byte[] proxyGenerator = ProxyGenerator.generateProxyClass("Proxy$User",new Class<?>[]{UserService.class});
//        OutputStream outputStream = new FileOutputStream("D:/bangsun/gitspace/spring-study/spring-study-04-proxy/src/test/java/com/bsfit/suaf/test"+"Proxy$User"+".class");
        OutputStream outputStream = new FileOutputStream("./src/test/java/com/bsfit/suaf/test"+"Proxy$User"+".class");
        outputStream.write(proxyGenerator);
    }
}
