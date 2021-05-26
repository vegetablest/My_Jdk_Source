package com.bsfit.suaf;

import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/5/25 11:17
 * @verson
 */
public class TestElasticSearchGet {

    private static TransportClient client;

    @Before
    public void initConn() throws Exception {
        String IP = "10.100.2.188";
        int PORT = 9300;
        //创建ES客户端 切记Java操作ES连接的是9300端口
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddresses(new TransportAddress(InetAddress.getByName(IP), PORT));
    }

    @Test
    public void testGet() {
        GetRequestBuilder requestBuilder = client.prepareGet("iks", "emp", "4");
        System.out.println(requestBuilder.get().getSourceAsString());
    }
}
