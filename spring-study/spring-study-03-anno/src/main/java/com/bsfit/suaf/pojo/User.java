package com.bsfit.suaf.pojo;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
//自动装配
@Component
@Scope("singleton")
@SuppressWarnings("all")
@Data
public class User {

    private String name = "suaf";
    /*注入，可用于set方法或者构造方法上*/
    @Value("suaf")
    private String userName;
}
