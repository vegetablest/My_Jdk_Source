package com.bsfit.suaf.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author bangsun
 */
@Data
public class Teacher {

    private int id;
    private String name;
    private List<Student> students;

}
