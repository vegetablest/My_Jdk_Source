package com.bsfit.suaf.mycomponent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/16 13:42
 * @verson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "suaf.test")
public class User {

    private String name;
    private int age;


}
