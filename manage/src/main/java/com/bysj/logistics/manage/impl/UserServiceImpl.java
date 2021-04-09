package com.bysj.logistics.manage.impl;

import com.bysj.logistics.manage.mapper.UserMapper;
import com.bysj.logistics.manage.pojo.User;
import com.bysj.logistics.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/4/6 9:27
 * @verson
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByUserName(String username) {
        return userMapper.selectByPrimaryKey(username);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Integer insertUser(User user) {
        return userMapper.insertSelective(user);
    }
}
