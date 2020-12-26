package com.bsfit.suaf.mapper;

import com.bsfit.suaf.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MapperTest {

    @Test
    public void test01() throws IOException {
        //配置文件删了，这里不行了

/*        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> user = mapper.selectUser();*/

        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapperImpl userMapperImpl = (UserMapperImpl)applicationContext.getBean("userMapperImpl");
        List<User> user = userMapperImpl.selectUser();
        for (User user1 : user) {
            System.out.println(user1);
        }
    }
    @Test
    public void test02() throws IOException {
        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapperImpl2 userMapperImpl = (UserMapperImpl2)applicationContext.getBean("userMapperImpl2");
        List<User> user = userMapperImpl.selectUser();
        for (User user1 : user) {
            System.out.println(user1);
        }
    }
}
