package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这里不能被@ComponentScan扫描到
 * @author bangsun
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        //定义为随机
        return new RandomRule();
    }
}
