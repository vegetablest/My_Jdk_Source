package com.bsfit.suaf;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bsfit.suaf.mapper.UserMapper;
import com.bsfit.suaf.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisPlusStudyTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void baseMapperTest(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void baseMapperInsertTest(){
        User user= new User();
        user.setEmail("12345678@163.com");
        user.setAge(23);
        user.setName("zhangsna");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }
}
