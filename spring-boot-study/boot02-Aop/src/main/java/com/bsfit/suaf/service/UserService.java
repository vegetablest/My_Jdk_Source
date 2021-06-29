package com.bsfit.suaf.service;

import com.bsfit.suaf.anno.LogPrint;
import com.bsfit.suaf.pojo.User;
import org.springframework.stereotype.Service;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/6/29 12:10
 * @verson 1.0
 */
public interface UserService {

    /**
     * return
     * */
    User findOne(Integer id);
}
