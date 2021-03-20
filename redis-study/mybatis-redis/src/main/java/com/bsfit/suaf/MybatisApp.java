package com.bsfit.suaf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/19 20:29
 * @verson
 */
@SpringBootApplication
@MapperScan("com.bsfit.suaf.mapper")
public class MybatisApp {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApp.class,args);
    }
}
