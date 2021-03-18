package com.bsfit.suaf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/18 21:31
 * @verson
 */
@SpringBootTest(classes = SessionApp.class)
@RunWith(SpringRunner.class)
public class RedisBoundApi {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 简化操作redisTemplate
     * */
    @Test
    public void testBound() throws Exception{

        BoundValueOperations<String, String> hahabound =
                stringRedisTemplate.boundValueOps("hahabound");
        hahabound.set("zhangsan");
        hahabound.append("好人");

        String s = hahabound.get();
        System.out.println(s);
    }
}
