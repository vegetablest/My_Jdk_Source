package com.bsfit.suaf.config;


import com.bsfit.suaf.pojo.User;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @author bangsun
 */
@Configurable
public class UserConfig {

    /*方法名字就是bean的name*/
    @Bean
    public User user1(){
        return new User();
    }
}
