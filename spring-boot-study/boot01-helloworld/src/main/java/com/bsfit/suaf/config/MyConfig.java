package com.bsfit.suaf.config;

import com.bsfit.suaf.pojo.Pet;
import com.bsfit.suaf.pojo.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <br>
 * 1、配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的
 * 2、配置类本身也是组件
 * 3、proxyBeanMethods：代理bean的方法
 *      Full(proxyBeanMethods = true)、【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
 *      Lite(proxyBeanMethods = false)【每个@Bean方法被调用多少次返回的组件都是新创建的】
 *      组件依赖必须使用Full模式默认。其他默认是否Lite模式
 * 个人理解：proxyBeanMethods = true 可以看作是工厂对象，通过方法获取user等同于BeanFactory.getClass
 *          proxyBeanMethods = false就是普通方法的调用
 *          这里是为了解决组件依赖的问题,为true的时候比较重量级，每次使用bean都会检查
 * @author bangsun
 * @data 2021/4/25 20:46
 * @verson
 */
@Configuration(proxyBeanMethods = true)
public class MyConfig {

    @Bean
    public User user(){
        /**
         * proxyBeanMethods = true,那么这里就是用的容器中的pet
         * */
        return new User().setPet(pet());
    }

    @Bean
    public Pet pet(){
        return new Pet();
    }

    @Bean
    @ConditionalOnMissingBean
    public Pet tom(){
        return new Pet();
    }
    @Bean
    @ConditionalOnBean
    public Pet tom2(){
        return new Pet();
    }
}
