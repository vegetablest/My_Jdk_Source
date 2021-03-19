package com.bsfit.suaf.impl;

import com.bsfit.suaf.pojo.Message;
import com.bsfit.suaf.service.DelayingQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/19 10:10
 * @verson
 */
@Service
public class DelayingQueueServiceImpl implements DelayingQueueService {

    @Autowired
    private RedisTemplate redisTemplate;
    private static final String DelayZSet = "Delay:Queue";

    @Override
    public List<Message> pull() {
        Set<Message> range = redisTemplate.opsForZSet().range(DelayZSet, 0, -1);
        List<Message> messages = new ArrayList<>(range);
        return messages;
    }

    @Override
    public void push(Message message) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        redisTemplate.opsForZSet().add(DelayZSet,message,message.getDelayTime());
    }

    @Override
    public void remove(Message message) {
        redisTemplate.opsForZSet().remove(DelayZSet,message);
    }
}
