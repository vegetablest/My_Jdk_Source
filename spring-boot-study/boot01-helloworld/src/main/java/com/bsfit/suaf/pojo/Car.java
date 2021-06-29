package com.bsfit.suaf.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/4/25 21:20
 * @verson
 */
@Data
@Component
@ConfigurationProperties(prefix = "car")
public class Car {
    private String name;
}
