package com.bsfit.suaf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bsfit.suaf.pojo.Person;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <br>
 * ES连接
 * @author bangsun
 * @data 2021/5/24 20:41
 * @verson
 */
public class TestElasticSearchConn {

    private static TransportClient client;

    @Before
    public void initConn() throws Exception {
        String IP = "10.100.2.188";
        int PORT = 9300;
        //创建ES客户端 切记Java操作ES连接的是9300端口
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddresses(new TransportAddress(InetAddress.getByName(IP), PORT));
    }

}
