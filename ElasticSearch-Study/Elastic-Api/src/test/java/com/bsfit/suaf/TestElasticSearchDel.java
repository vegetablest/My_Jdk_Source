package com.bsfit.suaf;

import org.elasticsearch.action.delete.DeleteRequestBuilder;
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
 * @data 2021/5/25 11:19
 * @verson
 */
public class TestElasticSearchDel {

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
    public void testDel(){
        DeleteRequestBuilder deleteRequestBuilder =
                client.prepareDelete("iks", "emp", "5");
        System.out.println(deleteRequestBuilder.get().status());
    }

}
