package com.bsfit.suaf;

import com.bsfit.suaf.pojo.User;
import com.bsfit.suaf.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/6/29 12:09
 * @verson 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppMain.class)
public class AopTest {

    @Autowired
    private UserService userService;

    @Test
    public void testUser() throws Exception{
        User one = userService.findOne(1);
    }
}
