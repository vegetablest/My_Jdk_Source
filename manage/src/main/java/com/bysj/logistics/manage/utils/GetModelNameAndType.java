package com.bysj.logistics.manage.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/4/2 22:16
 * @verson
 */
public class GetModelNameAndType {

    public static Map<String, Object> reflectToMap(Object model, Map<String, Object> map) throws SecurityException,
            NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        /*获取实体类的所有成员变量，返回Field数组*/
        Field[] fields = model.getClass().getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            /*获取属性类型,全类名*/
            String type = field.getGenericType().toString();
            /*关键,可访问私有变量*/
            field.setAccessible(true);
            /*将属性的首字母大写*/
            String upperCaseName =
                    name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
            if ("class java.lang.String".equals(type)) {
                /*如果type是class类型，则前面包含"class"，后面跟类名*/
                /*获取get方法*/
                Method m = model.getClass().getMethod("get" + upperCaseName);
                /*调用getter方法获取属性值*/
                String value = (String) m.invoke(model);
                if (value != null) {
                    map.put(name, value);
                }
            } else if ("class java.lang.Integer".equals(type) || "int".equals(type)) {
                Method m = model.getClass().getMethod("get" + upperCaseName);
                Integer value = (Integer) m.invoke(model);
                if (value != null) {
                    map.put(name, value);
                }
            } else if ("class java.lang.Short".equals(type) || "short".equals(type)) {
                Method m = model.getClass().getMethod("get" + upperCaseName);
                Short value = (Short) m.invoke(model);
                if (value != null) {
                    map.put(name, value);
                }
            } else if ("class java.lang.Double".equals(type) || "double".equals(type)) {
                Method m = model.getClass().getMethod("get" + upperCaseName);
                Double value = (Double) m.invoke(model);
                if (value != null) {
                    map.put(name, value);
                }
            } else if ("class java.lang.Boolean".equals(type) || "boolean".equals(type)) {
                Method m = model.getClass().getMethod("get" + upperCaseName);
                Boolean value = (Boolean) m.invoke(model);
                if (value != null) {
                    map.put(name, value);
                }
            } else if ("class java.util.Date".equals(type) ) {
                Method m = model.getClass().getMethod("get" + upperCaseName);
                Date value = (Date) m.invoke(model);
                if (value != null) {
                    map.put(name, value);
                }
            } else if ("class java.lang.Long".equals(type) || "long".equals(type)) {
                Method m = model.getClass().getMethod("get" + upperCaseName);
                Long value = (Long) m.invoke(model);
                if (value != null) {
                    map.put(name, value);
                }
            }
        }
        return map;
    }
}
