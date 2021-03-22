package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//全参
@AllArgsConstructor
//空参
@NoArgsConstructor
public class Payment {  
    private Long id;
    private String serial;
}
