package com.bsfit.suaf.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * <br>
 * 工厂类，用于获取spring创建好的工厂
 * @author bangsun
 * @data 2021/3/19 22:19
 * @verson
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    /**
     * spring会将将创建好的工厂以参数形式传递给你
     * */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /**
         * 将工厂保留下来
         * */
        this.applicationContext=applicationContext;
    }

    /**
     * 提供在工厂中获取对象的方法
     * */
    public static Object getBean(String beanName){

        return applicationContext.getBean(beanName);
    }
}
