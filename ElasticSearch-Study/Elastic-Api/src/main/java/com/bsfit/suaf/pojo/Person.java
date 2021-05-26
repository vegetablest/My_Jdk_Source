package com.bsfit.suaf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * <br>
 *
 * @author bangsun
 * @data 2021/5/25 11:07
 * @verson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Person {
    private String id;
    private String name;
    private Integer age;
    private String bir;
    private String context;
}
