package com.bsfit.suaf.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.bsfit.suaf.services.Send;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author bangsun
 */
@Service
public class SendImpl implements Send {
    public boolean send(String phoneNum, String templateCode, Map<String, Object> codeMap) {
        /**
         * 连接阿里云，第一个参数不动，后面是信息服务的账号和密码
         * 注意密码只生成一次，最号记下来
         * */
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GEMGoTZuD5QENdkK2hY", "VST8yCymxm8PGRVj6U3bVqRgn0fbfR");
        /*生产连接*/
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        /*请求方式*/
        request.setSysMethod(MethodType.POST);
        /*官方域不要动*/
        request.setSysDomain("dysmsapi.aliyuncs.com");
        /*官方版本号不要动*/
        request.setSysVersion("2017-05-25");
        /*发送短信的事件*/
        request.setSysAction("SendSms");
        /*自定义参数{手机号，验证码，签名，模板}*/
        request.putQueryParameter("PhoneNumbers", phoneNum);
        /*申请的签名结果*/
        request.putQueryParameter("SignName", "快乐购物网");
        /*申请的模板结果，填的是编码*/
        request.putQueryParameter("TemplateCode", templateCode);
        /*构建一个短信验证码*/
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(codeMap));
        try {
            /*发送验证码*/
            CommonResponse response = client.getCommonResponse(request);
            /*返回结果*/
            System.out.println(response.getData());
//            return (response.getData().contains("SUCCESS"))?true:false;
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}

