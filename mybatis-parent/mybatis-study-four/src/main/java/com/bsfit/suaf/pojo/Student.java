package com.bsfit.suaf.pojo;

import lombok.Data;

/**
 * @author bangsun
 */
@Data
public class Student {
    private int id;
    private String name;
    private Teacher teacher;
}
