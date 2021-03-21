package com.bsfit.suaf.service;

import com.bsfit.suaf.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/21 20:43
 * @verson
 */
public interface UserService {

    List<User> findAll();
    void addUser(List<User> user);
}
