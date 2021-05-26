package com.bsfit.suaf;

import com.alibaba.fastjson.JSON;
import com.bsfit.suaf.pojo.Person;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
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
 *
 * @author bangsun
 * @data 2021/5/25 11:18
 * @verson
 */
public class TestElasticSearchPut {

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
    public void testPut() {
        Map<String,Object> result = new HashMap<>(16);
        result.put("name","小黑的故事改名字啦");
        UpdateRequestBuilder updateRequestBuilder =
                client.prepareUpdate("iks", "emp", "4").setDoc(result);
        System.out.println(updateRequestBuilder.get());
    }

    /**
     * 批量更新操作
     * */
    @Test
    public void testBulk() {
        Person person = new Person().setId(UUID.randomUUID().toString().substring(0,8))
                .setName("悲伤的故事").setAge(33).setBir("2021-05-25")
                .setContext("ES文件浏览器是一个能管理移动设备、局域网共享、远程FTP、蓝牙设备和云存储的系统工具类移动软件。");
        String jsonString = JSON.toJSONString(person);
        /*索引一条记录*/
        IndexRequest indexRequest = new IndexRequest("iks", "emp", person.getId())
                .source(jsonString, XContentType.JSON);
        /*删除一条索引*/
        DeleteRequest deleteRequest = new DeleteRequest("iks", "emp", "25865523");
        /*更新记录*/
        Person update = new Person().setId("27619690").setName("吹牛大王");
        String updateString = JSON.toJSONString(update);
        UpdateRequest updateRequest = new UpdateRequest("iks", "emp", update.getId()).doc(updateString,XContentType.JSON);

        BulkResponse bulkItemResponses =
                client.prepareBulk().add(indexRequest).add(deleteRequest).add(updateRequest).get();
        BulkItemResponse[] items = bulkItemResponses.getItems();
        for (BulkItemResponse item : items) {
            System.out.println(item.status());
        }
    }

    @Test
    public void testBulk2() {
        Person person = new Person().setId(UUID.randomUUID().toString().substring(0,8))
                .setName("悲伤的故事").setAge(33).setBir("2021-05-25")
                .setContext("ES文件浏览器是一个能管理移动设备、局域网共享、远程FTP、蓝牙设备和云存储的系统工具类移动软件。");
        String jsonString = JSON.toJSONString(person);
        /*索引一条记录*/
        IndexRequest indexRequest = new IndexRequest("iks", "emp", person.getId())
                .source(jsonString, XContentType.JSON).opType("create");

        BulkResponse bulkItemResponses =
                client.prepareBulk().add(indexRequest).get();
        BulkItemResponse[] items = bulkItemResponses.getItems();
        for (BulkItemResponse item : items) {
            System.out.println(item.status());
        }
    }
}