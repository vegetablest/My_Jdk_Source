package com.bsfit.suaf.pojo;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiTest {

    @Test
    public void studentTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) applicationContext.getBean("student");
        Address address = (Address) applicationContext.getBean("address");
        System.out.println(address);
        System.out.println(student);
    }
    /*复杂类型注入*/
    @Test
    public void studentTest2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) applicationContext.getBean("student2");
        Address address = (Address) applicationContext.getBean("address");
        System.out.println(address);
        System.out.println(student);
    }
    /*PCnamespace注入*/
    @Test
    public void studentTestPC(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        User userP =  applicationContext.getBean("userP",User.class);
        User userC =  applicationContext.getBean("userC",User.class);
        System.out.println(userP);
        System.out.println(userC);
    }
    /*bean作用域*/
    @Test
    public void studentTestScope(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        User userSin1 =  applicationContext.getBean("userSin",User.class);
        User userSin2 =  applicationContext.getBean("userSin",User.class);
        User userPro1 =  applicationContext.getBean("userPro",User.class);
        User userPro2 =  applicationContext.getBean("userPro",User.class);
        System.out.println(userSin1 == userSin2);
        System.out.println(userPro1 == userPro2);
    }
    /*手动装配*/
    @Test
    public void studentTestS(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        People people =  applicationContext.getBean("people",People.class);
        people.getCat().shout();
        people.getDog().shout();
        System.out.println(people);
    }
    /*自动装配byName*/
    @Test
    public void studentTestZ(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        People people =  applicationContext.getBean("people2",People.class);
        people.getCat().shout();
        people.getDog().shout();
        System.out.println(people);
    }
    /*自动装配byType*/
    @Test
    public void studentTestZ3(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        People people =  applicationContext.getBean("people3",People.class);
        people.getCat().shout();
        people.getDog().shout();
        System.out.println(people);
    }
}
