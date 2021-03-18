package com.bsfit.suaf.config;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/15 10:11
 * @verson
 */
@SuppressWarnings("all")
public class MyStringRedisSerializer implements RedisSerializer<Object> {
    private final Charset charset;

    public MyStringRedisSerializer() {
        this(StandardCharsets.UTF_8);
    }

    public MyStringRedisSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }

    @Override
    public String deserialize(byte[] bytes) {
        return (bytes == null ? null : new String(bytes, charset));
    }

    /**
     * 将对象序列化为byte[]数组
     */
    @Override
    public byte[] serialize(Object object) {
/*        //如果这个对象为null
        if (object == null) {
            return new byte[0];
        }
        //如果这个对象时String类型
        if (object instanceof String) {
            return object.toString().getBytes(charset);
            //如果这个对象不是String类型，就将这个对象转换为String类型后，然后转成byte字节数组
        } else {
            String string = JSON.toJSONString(object);
            return string.getBytes(charset);
        }*/
        return null;
    }
}
