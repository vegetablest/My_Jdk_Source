package com.bsfit.suaf.controller;

import com.bsfit.suaf.services.Send;
import com.bsfit.suaf.services.impl.SendImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author bangsun
 * @author bangsun
 */
@RestController
/**跨域支持,不同微服务之间*/
@CrossOrigin
public class SendController {

    @Autowired
    private Send send;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @GetMapping("/send/{phone}")
    public String sendCode(@PathVariable("phone") String phone){
        /*从redis拿数据发送*/
        String code = redisTemplate.opsForValue().get(phone);
        if (code != null && !code.equals("")){
            return phone+":"+"还未过期！";
        }else {
            /*生产验证码平且存储redis后发送*/
            code = UUID.randomUUID().toString().substring(0, 6);
            HashMap<String,Object> param = new HashMap<String, Object>();
            param.put("code",code);
            boolean isSend = send.send(phone, "SMS_205137906", param);
            if (isSend){
                redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
                return phone+":"+code+":"+"发送成功！";
            }else {
                return phone+":"+code+":"+"发送失败！";
            }
        }
    }


}
