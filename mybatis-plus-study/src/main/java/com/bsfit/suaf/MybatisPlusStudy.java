package com.bsfit.suaf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author bangsun
 */
@SpringBootApplication
@MapperScan("com.bsfit.suaf.mapper")
/*扫描mapper文件夹*/
public class MybatisPlusStudy {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusStudy.class,args);
    }
}
