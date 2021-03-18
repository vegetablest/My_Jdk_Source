package com.bsfit.suaf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * <br>
 * @EnableRedisHttpSession开启分布式redis session模式
 * @author bangsun
 * @data 2021/3/17 20:54
 * @verson
 */
@Configuration
@EnableRedisHttpSession //加这个注解将全局的session数据存入redis
public class RedisSessionManager {
}
