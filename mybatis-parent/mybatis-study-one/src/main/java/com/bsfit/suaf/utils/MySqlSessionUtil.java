package com.bsfit.suaf.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author bangsun
 */
public class MySqlSessionUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static{
        try {
            /*获取工厂实例*/
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession(){
        /*获取sqlSession,里面有所有操作数据库的方法*/
        return sqlSessionFactory.openSession();
    }
}
