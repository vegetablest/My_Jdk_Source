package com.bsfit.suaf;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/17 9:23
 * @verson
 */
public class TestLang {
    public static void main(String[] args) {
        StringUtils.isBlank("");
        StringUtils.isEmpty("");
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("boolean",false);
        hashMap.put("boolean1","");
        Boolean aBoolean = MapUtils.getBoolean(hashMap, "boolean", true);
        System.out.println(aBoolean);
        Boolean boolean1 = MapUtils.getBooleanValue(hashMap, "boolean1", true);
        System.out.println(boolean1);
    }
}
