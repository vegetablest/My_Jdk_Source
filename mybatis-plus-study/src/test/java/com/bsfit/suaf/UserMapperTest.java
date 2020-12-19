package com.bsfit.suaf;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bsfit.suaf.mapper.UserMapper;
import com.bsfit.suaf.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
@Slf4j
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void userMapperTest(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
    @Test
    public void userMapperTestInsert(){
        User user = new User();
        user.setName("gfdsa");
        user.setAge(23);
        user.setEmail("23456789");
        int hao = userMapper.insert(user);
        log.info(String.valueOf(hao));
    }
    @Test
    public void userMapperTestUpdate(){
        User user = new User();
        user.setId(15);
        user.setName("xxx");
        user.setEmail("vvv");
        int huai = userMapper.updateById(user);
        System.out.println(huai);
    }
    @Test
    public void userMapperTestSel(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }
    /**
     * 测试乐观锁成功
     * */
    @Test
    public void userMapperTestLockSuccess(){
        /*查询用户信息*/
        User user = userMapper.selectById(15);
        /*修改用户*/
        user.setName("盲僧");
        int i = userMapper.updateById(user);
        System.out.println("i = " + i);
    }
    /**
     * 测试乐观锁失败
     * */
    @Test
    public void userMapperTestLockFaild(){
        User user1 = userMapper.selectById(15);
        user1.setName("didid");
        user1.setAge(20);
        User user2 = userMapper.selectById(15);
        user2.setName("wuwuw");
        user2.setAge(30);

        int huai2 = userMapper.updateById(user2);
        int huai1 = userMapper.updateById(user1);
    }


    @Test
    public void userMapperIds(){
        User user = userMapper.selectById(1);
        System.out.println(user);
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4));
        users.forEach(System.out::println);
    }
    @Test
    public void userMapperSelect(){
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("name","hao");
        stringObjectHashMap.put("age",23);
        List<User> users = userMapper.selectByMap(stringObjectHashMap);
        users.forEach(System.out::println);
    }
    @Test
    public void userMapperPage(){
        /*limit pageHelper 分页,第一页，显示五条*/
        Page<User> page = new Page<>(2,5);
        userMapper.selectPage(page, null);
        page.getOrders().forEach(System.out::println);
        /*底层先count*/
        long total = page.getTotal();
        System.out.println(total);
    }
    @Test
    public void userMapperDelete(){
        int i = userMapper.deleteById(1);
        int i1 = userMapper.deleteBatchIds(Arrays.asList(2, 3, 4));
        System.out.println(i);
        System.out.println(i1);
        HashMap<String,Object> hashMap = new HashMap<>();
        Object put = hashMap.put("name", "haha");
        userMapper.deleteByMap(hashMap);
    }
    @Test
    public void userMapperDeleteLuoJi(){
        userMapper.deleteById(6);
    }
    @Test
    public void userMapperWapper(){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.like("name","ha").isNotNull("age").
                eq("email","haha").ge("id",7);
        wrapper.likeLeft("name","a").likeRight("name","b");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

}
