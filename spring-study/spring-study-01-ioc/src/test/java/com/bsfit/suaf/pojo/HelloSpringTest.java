package com.bsfit.suaf.pojo;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringTest {
    @Test
    public void beansTean() {
        //获取spring的上下文对对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        //对象交给spring的上下文管理了
        HelloWorld helloWorld = (HelloWorld) applicationContext.getBean("helloWorld");
        System.out.println(helloWorld.toString());
    }

    @Test
    public void beansUser() {
        //获取spring的上下文对对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        //对象交给spring的上下文管理了,会先输出user的无参构造方法，说明通过了构造方法去创建的bean
        UserT user = (UserT) applicationContext.getBean("user");
        UserT user1 = (UserT) applicationContext.getBean("user1");

        System.out.println(user.toString());
    }

    @Test
    public void beansUser1() {
        //获取spring的上下文对对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        //对象交给spring的上下文管理了,会先输出user的有参构造方法，说明通过了构造方法去创建的bean
        UserT user1 = (UserT) applicationContext.getBean("user1");
        System.out.println(user1.toString());
        //默认单例，就一个，可开启多例与懒加载
        UserT user2 = (UserT) applicationContext.getBean("user1");
        System.out.println(user1 == user2);
    }

    @Test
    public void beansUser2() {
        //获取spring的上下文对对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        //对象交给spring的上下文管理了,会先输出user的有参构造方法，说明通过了构造方法去创建的bean
        UserT user2 = (UserT) applicationContext.getBean("user2");
        System.out.println(user2.toString());
    }

    @Test
    public void beansUser3() {
        //获取spring的上下文对对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        //对象交给spring的上下文管理了,会先输出user的有参构造方法，说明通过了构造方法去创建的bean
        UserT user3 = (UserT) applicationContext.getBean("user3");
        System.out.println(user3.toString());
    }
    @Test
    public void beansUserTAlis() {
        //获取spring的上下文对对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        //对象交给spring的上下文管理了,会先输出user的有参构造方法，说明通过了构造方法去创建的bean
        UserT userT = (UserT) applicationContext.getBean("newUserT");
        System.out.println(userT.toString());
    }
    @Test
    public void beansUserTAlis2() {
        //获取spring的上下文对对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        //对象交给spring的上下文管理了,会先输出user的有参构造方法，说明通过了构造方法去创建的bean
        UserT userT = (UserT) applicationContext.getBean("t");
        System.out.println(userT.toString());
    }
    @Test
    public void beansUserImport() {
        //获取spring的上下文对对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //对象交给spring的上下文管理了,会先输出user的有参构造方法，说明通过了构造方法去创建的bean
        User user4 = (User) applicationContext.getBean("user4");
        System.out.println(user4.toString());
    }
}
