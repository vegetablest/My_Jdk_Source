package com.bysj.logistics.utils;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/4/4 10:28
 * @verson
 */
public class DatabaseUtil {

    public enum DatabaseType {
        MYSQL,
        ORACLE,
        OTHER
    }

    public static String getSchemaName(String ... args){
        StringBuffer result = new StringBuffer();
        String temp = args[0];
        if(temp.contains("mysql")){
            if(temp.contains("?")){
                temp = temp.replace("?", "~");
                temp = temp.split("~")[0];
            }
            result.append(temp.split("/")[temp.split("/").length-1].trim());
        }else if(temp.contains("oracle")){
            result.append(args[1].trim());
        }
        return result.toString().toUpperCase();
    }

    public static DatabaseType getDatabaseType(String ... args){
        StringBuffer result = new StringBuffer();
        String temp = args[0];
        if(temp.contains("mysql")){
            return DatabaseType.MYSQL;
        }else if(temp.contains("oracle")){
            return DatabaseType.ORACLE;
        }
        return DatabaseType.OTHER;
    }

    public static boolean isMysql(String... args){
        return getDatabaseType(args).equals(DatabaseType.MYSQL);
    }

    public static boolean isOracle(String... args){
        return getDatabaseType(args).equals(DatabaseType.MYSQL);
    }

}

