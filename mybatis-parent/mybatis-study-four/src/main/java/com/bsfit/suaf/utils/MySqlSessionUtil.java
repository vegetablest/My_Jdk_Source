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
            /*SqlSessionFactoryBuilder工厂模式，仅仅是为了创建sqlSessionFactory，一旦创建了sqlSessionFactory
            * SqlSessionFactoryBuilder工厂可以不要了，所以SqlSessionFactoryBuilder作用域就是局部变量用完就不要了
            * 相反sqlSessionFactory像个池子，自始至终都是需要的,随时待命去创建sqlSession，创建之后一直要用，所以他的作用域是全局的
            * sqlSession应该是用完就关闭
            * */

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession(){
        /*获取sqlSession,里面有所有操作数据库的方法*/
//        return sqlSessionFactory.openSession();
        /*自动提交事务*/
        return sqlSessionFactory.openSession(true);
    }
}
