package com.bsfit.suaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <br>
 * spring的分布式session共享和mamcahed模式不同
 * mamcached的session共享是在tomcat层面配置，Mamcahed整合tomcat之后所有tomcat应用都会将session放入Mamcached
 * 而redis是配置了过滤器将session放入redis，请求redis获取，这样并不是针对对应tomcat应用
 *
 * 打包放入服务器部署，nginx代理，然后测试
 * @author bangsun
 * @data 2021/3/17 20:48
 * @verson
 */
@SpringBootApplication
public class SessionApp {
    public static void main(String[] args) {
        SpringApplication.run(SessionApp.class,args);
    }
}
