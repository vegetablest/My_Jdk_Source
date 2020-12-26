package com.bsfit.suaf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class User {
    private long id;
    private String name;
    private Integer age;
    private Date insertTime;
    private Date updateTime;
    private String email;
    private Integer version;
    private Integer delete;
}
