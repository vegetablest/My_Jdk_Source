package com.bsfit.suaf.dao;

import com.bsfit.suaf.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/21 20:39
 * @verson
 */
@Mapper
public interface UserDao {
    List<User> findAll();
    void addUser(User user);
}
