package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 支付模块的返回pojo
 * 状态码、信息、data
 * @author bangsun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable {

    private Integer code;
    private String message;
    private T  data ;
    /**
     * 两个参数构造方法
     */
    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
