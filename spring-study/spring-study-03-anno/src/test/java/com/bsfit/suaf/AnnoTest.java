package com.bsfit.suaf;


import com.bsfit.suaf.config.UserConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("all")
public class AnnoTest {


    @Test
    public void annotTest01(){
        /*使用注解依旧能放入上下文中去取*/
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object user = applicationContext.getBean("user");
        System.out.println(user.toString());
    }
    @Test
    public void annotTest02(){
        /*使用注解依旧能放入上下文中去取*/
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(UserConfig.class);
        Object user = applicationContext.getBean("user1");
        System.out.println(user.toString());
    }
}
