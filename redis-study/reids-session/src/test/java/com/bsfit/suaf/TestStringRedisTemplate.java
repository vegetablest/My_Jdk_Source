package com.bsfit.suaf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/18 19:20
 * @verson
 */
@SpringBootTest(classes = SessionApp.class)
@RunWith(SpringRunner.class)
public class TestStringRedisTemplate {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testBase() throws Exception{
        /**五种类型操作*/
        stringRedisTemplate.opsForValue().set("zhangsanfeng","小晨");
        String zhangsanfeng = stringRedisTemplate.opsForValue().get("zhangsanfeng");
        System.out.println("zhangsanfeng = " + zhangsanfeng);
//        stringRedisTemplate.opsForList();
//        stringRedisTemplate.opsForSet();
//        stringRedisTemplate.opsForZSet();
//        stringRedisTemplate.opsForHash();
    }

    /**
     * key的操作
     * */
    @Test
    public void testKey() throws Exception{
        stringRedisTemplate.opsForValue().set("testKey","小晨");
        Boolean exists = stringRedisTemplate.hasKey("testKey");
        System.out.println("exists = " + exists);
        DataType type = stringRedisTemplate.type("testKey");
        System.out.println("type = " + type);
        stringRedisTemplate.delete("testKey");
        exists = stringRedisTemplate.hasKey("testKey");
        System.out.println("exists = " + exists);
        Long ttl = stringRedisTemplate.getExpire("testKey");
        System.out.println("testKey = " + ttl);
        String randomKey = stringRedisTemplate.randomKey();
        System.out.println("randomKey = " + randomKey);
        /**key不存在抛错*/
        stringRedisTemplate.rename("xxxxx", "yyyyy");
        /**先判断key存在不，不存在不抛错*/
        Boolean renameIfAbsent = stringRedisTemplate.renameIfAbsent("xxxxx", "yyyyy");
        System.out.println("renameIfAbsent = " + renameIfAbsent);

    }

    @Test
    public void testString() throws Exception{
        stringRedisTemplate.opsForValue().set("timeout","timeout",10000, TimeUnit.MILLISECONDS);
        stringRedisTemplate.opsForValue().append("timeout","xiaoming");
        stringRedisTemplate.opsForValue().increment("upupup",10);
        Long increment = stringRedisTemplate.opsForValue().increment("upupup");
        System.out.println(increment);
    }


    @Test
    public void testList() throws Exception{
        stringRedisTemplate.opsForList().leftPush("listsss","小明");
        stringRedisTemplate.opsForList().leftPushAll("listsss","小青","小猪","笑话");

        String listsss = stringRedisTemplate.opsForList().leftPop("listsss");
        System.out.println("listsss = " + listsss);

        List<String> listsss1 = stringRedisTemplate.opsForList().range("listsss", 0, -1);
        for (String s : listsss1) {
            System.out.println(s);
        }

        stringRedisTemplate.opsForList().trim(listsss,1,3);
    }

    @Test
    public void testSet() throws Exception{
        stringRedisTemplate.opsForSet().add("setsss","小王","小王","小张","小三");
        Set<String> setsss = stringRedisTemplate.opsForSet().members("setsss");
        for (String s : setsss) {
            System.out.println(s);
        }
        Long setsss1 = stringRedisTemplate.opsForSet().size("setsss");
        System.out.println("setsss1 = " + setsss1);
    }

    @Test
    public void testZSet() throws Exception{
        stringRedisTemplate.opsForZSet().add("zsetsss","张三",100);
        stringRedisTemplate.opsForZSet().add("zsetsss","李四",90);
        Set<String> zsetsss = stringRedisTemplate.opsForZSet().range("zsetsss", 0, -1);
        for (String s : zsetsss) {
            System.out.println(s);
        }
        Set<ZSetOperations.TypedTuple<String>> zsetsss2 = stringRedisTemplate.opsForZSet().rangeWithScores("zsetsss", 0, -1);
        for (ZSetOperations.TypedTuple<String> stringTypedTuple : zsetsss2) {
            System.out.println(stringTypedTuple.getValue()+":"+stringTypedTuple.getScore());
        }
    }

    @Test
    public void testHash() throws Exception{
        stringRedisTemplate.opsForHash().put("mapsss","zhangsan","23");
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("lisi","24");
        hashMap.put("wangwu","25");
        stringRedisTemplate.opsForHash().putAll("mapsss",hashMap);

        Object o = stringRedisTemplate.opsForHash().get("mapsss", "zhangsan");
        System.out.println(o);

        List<Object> mapsss2 = stringRedisTemplate.opsForHash().values("mapsss");
        for (Object o1 : mapsss2) {
            System.out.println(o1);
        }
        Set<Object> mapsss = stringRedisTemplate.opsForHash().keys("mapsss");
        for (Object mapsss1 : mapsss) {
            System.out.println(mapsss1);
        }

    }
}
