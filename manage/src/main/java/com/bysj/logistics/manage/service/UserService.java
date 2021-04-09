package com.bysj.logistics.manage.service;

import com.bysj.logistics.manage.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/4/2 22:46
 * @verson
 */
@Service
public interface UserService {

    public User selectByUserName(String username);

    public List<User> findAll();

    public Integer insertUser(User user);
}
