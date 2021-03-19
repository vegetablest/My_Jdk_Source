package com.bsfit.suaf.impl;

import com.bsfit.suaf.service.RedisMQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/19 9:46
 * @verson
 */
@Service
public class RedisMQServiceImpl implements RedisMQService {

    @Autowired
    private RedisTemplate redisTemplate;

    private static Logger log = LoggerFactory.getLogger(RedisMQServiceImpl.class);

    private static final String MESSAGE_KEY = "message:queue";

    @Override
    public void produce(String string) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //生产者把消息丢到消息队列中
        redisTemplate.opsForList().leftPush(MESSAGE_KEY, string);
    }

    @Override
    public void consume() {
        String string = (String) redisTemplate.opsForList().rightPop(MESSAGE_KEY);
        //消费方拿到消息后进行业务处理
        log.info("consume : {}", string);
    }
}
