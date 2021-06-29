package com.bsfit.suaf.impl;

import com.bsfit.suaf.anno.LogPrint;
import com.bsfit.suaf.pojo.User;
import com.bsfit.suaf.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/6/29 12:12
 * @verson 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @LogPrint
    @Override
    public User findOne(Integer id) {
        return new User().setId(id).setUserName("zhangsna").setPassWord("zhangsan");
    }
}
