package com.bsfit.suaf.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/19 20:33
 * @verson
 */
@Data
@Accessors(chain = true)
public class Course implements Serializable {
    private String cId;
    private String cName;
    private String tId;
}
