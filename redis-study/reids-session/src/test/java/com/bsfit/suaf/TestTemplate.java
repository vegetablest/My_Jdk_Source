package com.bsfit.suaf;

import com.bsfit.suaf.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;
import sun.applet.Main;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/18 21:03
 * @verson
 */
@SpringBootTest(classes = SessionApp.class)
@RunWith(SpringRunner.class)
public class TestTemplate {
    /**
     * 注入RedisTemplate key和value是需要序列化的，
     * 通过序列化的key才能取到序列化的value，zhangsanfeng是stringRedisTemplate没有序列化之后存进去的
     * 而RedisTemplate序列化zhangsanfeng之后去拿的所以拿不到啊
     * 使用注入RedisTemplate放入实现序列化的User，再去取可以取到
     */
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testKey() throws Exception{
        Object zhangsanfeng = redisTemplate.opsForValue().get("zhangsanfeng");
        /**输出null*/
        System.out.println(zhangsanfeng);
    }

    @Test
    public void test() throws Exception{
        /**
         * 存进去的东西去终端里边都get不到，因为终端那里没有序列化key
         *
         * 所以为了终端或者可视化界面能够get到数据，我们应该自定义序列化方式，
         * string类型的key不进行序列化，保证怎么取怎么有，并不是只能用redisTemplate取
         *
         * 默认redisTemplate使用的是JDK的序列化方式
         * stringRedisTemplate使用的是redis的序列化方式
         * */
        User user = new User("序列化",23);
        /**将key序列化方式进行更改*/
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set("user",user);
        redisTemplate.opsForList().leftPush("userlist",user);
        redisTemplate.opsForSet().add("userset",user);
        redisTemplate.opsForZSet().add("userzset",user,10);
        /**问题来了，hash两个key怎么办？*/
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForHash().put("userhash","user",user);

        Object user1 = redisTemplate.opsForValue().get("user");
        System.out.println(user1);

        RedisSerializer keySerializer = redisTemplate.getKeySerializer();
        System.out.println(keySerializer);
    }
}
