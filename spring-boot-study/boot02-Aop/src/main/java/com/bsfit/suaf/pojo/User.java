package com.bsfit.suaf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/6/29 12:10
 * @verson 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {

    private Integer id;
    private String userName;
    private String passWord;
}
