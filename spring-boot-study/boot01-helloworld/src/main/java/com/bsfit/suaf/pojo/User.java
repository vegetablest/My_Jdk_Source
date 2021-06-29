package com.bsfit.suaf.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/4/25 20:46
 * @verson
 */
@Data
@Accessors(chain = true)
public class User {
    private String name;
    private Pet pet;
}
