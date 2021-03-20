package com.bsfit.suaf.cache;

import com.bsfit.suaf.utils.ApplicationContextUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.DigestUtils;

import java.util.concurrent.TimeUnit;

/**
 * <br>
 * 自定义redis实现mybatis的cache
 * 但是这个类不是工厂管理的不能注入restTemplate
 * -1660246649:678206116:com.bsfit.suaf.mapper.CourseMapper.getFindAll:0:2147483647:select c_id,c_name,t_id from course;:SqlSessionFactoryBean,
 * 放入的value是:[Course(cId=01, cName=语文, tId=02), Course(cId=02, cName=数学, tId=01), Course(cId=03, cName=英语, tId=03)]
 *
 * 默认是只清空自己namespace的缓存，单表没问题，但是多表关联查询就会出现问题
 * 所以单表的时候这样没问题，多表时候确定了表关联关系时，cache标签使用不行了，使用cache-ref标签，
 * 让两个关系的mapper进行关联，一个更改两个删除
 * 
 * key太长怎么重新设计key?  推荐MD5对key加密使key变的简洁，
 * MD5加密，不同的文件加密出来的结果不同，相同文件多次使用MD5加密结果相同。
 * 如何判断两个文件是否一样？MD5加密之后比较即可
 *
 * mybatis缓存能够有效避免缓存穿透，因为就算是查到null也会将null放入缓存
 * 如果非要自己写缓存要记住这点
 * @author bangsun
 * @data 2021/3/19 22:04
 * @verson
 */
public class RedisCache implements Cache {
    /**放入缓存的namespace，别问，问就是规定*/
    private final String id;

    /**
     * 必须要id类型的构造,没有会报错
     */
    public RedisCache(String id) {
        System.out.println("====================》"+id);
        this.id = id;
    }

    /**
     * 返回cache的唯一标识，不能为null
     * */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * 缓存放入值
     */
    @Override
    public void putObject(Object key, Object value) {
        System.out.println("缓存放入值");
        System.out.println(String.format("放入的key是:%s,放入的value是:%s",key,value));
        RedisTemplate redisTemplate = getRedisTemplate();

        /**使用redis的hash作为缓存*/
        redisTemplate.opsForHash().put(id.toString(),getMD5(key.toString()),value);
        /**整个一小时过期时间，根据不同的业务模块设置不同的时间，不能暴力全设置一小时*/
        if (id.equals("com.bsfit.suaf.mapper.CourseMapper")){
            System.out.println("科目模块设置超时时间1小时");
//        redisTemplate.expire(id.toString(),1, TimeUnit.HOURS);
        }
        if (id.equals("com.bsfit.suaf.mapper.ScoreMapper")){
            System.out.println("成绩模块设置超时时间12小时");
//        redisTemplate.expire(id.toString(),12, TimeUnit.HOURS);
        }
    }

    /**
     * 缓存取值
     */
    @Override
    public Object getObject(Object key) {
        System.out.println("缓存获取值");
        System.out.println(String.format("缓存中取值的key是:%s",key));
        RedisTemplate redisTemplate = getRedisTemplate();
        /**记得序列化，两次都是命中缓存*/
        Object o = redisTemplate.opsForHash().get(id.toString(), getMD5(key.toString()));
        System.out.println("前往redis拿到数据："+o);
        return o;
    }

    /**
     * 根据指定的key删除缓存，mybatis保留方法，未使用
     * */
    @Override
    public Object removeObject(Object key) {

        return null;
    }

    /**
     * 指定key清空缓存，其实每次调用增删改都会调用这个方法
     * */
    @Override
    public void clear() {
        System.out.println("这里应该清空缓存");
        RedisTemplate redisTemplate = getRedisTemplate();

        /**清空该模块的所有内容*/
        redisTemplate.delete(id.toString());
    }

    /**
     * 计算缓存的数量
     * */
    @Override
    public int getSize() {
        RedisTemplate redisTemplate = getRedisTemplate();
        System.out.println("计算缓存数量");
        int size = redisTemplate.opsForHash().size(id.toString()).intValue();

        return size;
    }

    /**
     * 封装getRedisTemplate，自己用
     * */
    private RedisTemplate getRedisTemplate() {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
    /**
     * 封装getRedisTemplate，自己用
     * */
    private String getMD5(String key) {
        String md5 = DigestUtils.md5DigestAsHex(key.getBytes());
        return md5;
    }
}
