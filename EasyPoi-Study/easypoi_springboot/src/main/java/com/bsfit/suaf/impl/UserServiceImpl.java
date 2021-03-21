package com.bsfit.suaf.impl;

import com.bsfit.suaf.dao.UserDao;
import com.bsfit.suaf.pojo.User;
import com.bsfit.suaf.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/21 20:44
 * @verson
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void addUser(List<User> user) {
        user.forEach(u -> {
            u.setId(null);
            String fileName = u.getPhoto().substring(u.getPhoto().lastIndexOf("\\") + 1);
            u.setPhoto(fileName);
            userDao.addUser(u);
        });
    }


}
