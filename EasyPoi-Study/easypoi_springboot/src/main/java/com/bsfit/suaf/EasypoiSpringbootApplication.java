package com.bsfit.suaf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bsfit.suaf.dao")
public class EasypoiSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasypoiSpringbootApplication.class, args);
	}

}
