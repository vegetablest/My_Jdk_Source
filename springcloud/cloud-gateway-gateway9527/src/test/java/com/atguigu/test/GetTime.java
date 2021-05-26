package com.atguigu.test;

import org.junit.Test;

import java.time.ZonedDateTime;

/**
 * <br>
 * 获取时区时间，配置路由断言
 * @author bangsun
 * @data 2021/5/16 12:01
 * @verson
 */
public class GetTime {

    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }

}
