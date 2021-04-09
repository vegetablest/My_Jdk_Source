package com.bysj.logistics.manage.utils;

import java.util.UUID;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/4/8 23:04
 * @verson
 */
public class UUIDToken {
    public static String getToken() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase().substring(0, 12);
    }
}
