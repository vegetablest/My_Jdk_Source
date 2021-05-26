package com.bsfit.suaf;

import com.alibaba.fastjson.JSON;
import com.bsfit.suaf.pojo.Person;
import org.elasticsearch.action.index.IndexResponse;
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
 * @data 2021/5/25 11:19
 * @verson
 */
public class TestElasticSearchPost {

    private static TransportClient client;

    @Before
    public void initConn() throws Exception {
        String IP = "10.100.2.188";
        int PORT = 9300;
        //创建ES客户端 切记Java操作ES连接的是9300端口
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddresses(new TransportAddress(InetAddress.getByName(IP), PORT));
    }

    /**
     * 添加数据
     * PUT /iks/emp/1
     * {
     *   "id":31,
     *   "name":"小黑的故事",
     *   "age":28,
     *   "bir":"2020-05-25",
     *   "context":"很多人学习elasticSearch都是自学,想百度一下如何重启es也是没有答案,我硬着头皮,算是琢磨出来了"
     * }
     * */
    @Test
    public void testCreate() {
        Map<String,Object> result = new HashMap<>(16);
        result.put("id",31);
        result.put("name","小黑的故事");
        result.put("age",28);
        result.put("bir","2020-05-25");
        result.put("context","很多人学习elasticSearch都是自学,想百度一下如何重启es也是没有答案,我硬着头皮,算是琢磨出来了");
        IndexResponse indexResponse = client.prepareIndex("iks", "emp", "4") //指定id会作为_id，不指定自动生成一个
                .setSource(result)  //设置数据
                .get();  //发送
        System.out.println("返回结果:"+indexResponse);
    }
    @Test
    public void testCreatePojo() {
        Person person = new Person().setId(UUID.randomUUID().toString().substring(0,8))
                .setName("小白的故事").setAge(23).setBir("2021-05-25")
                .setContext("es的一个概念就是去中心化，字面上理解就是无中心节点，这是对于集群外部来说的");
        String jsonString = JSON.toJSONString(person);
        IndexResponse indexResponse =
                client.prepareIndex("iks", "emp", person.getId()) //指定id会作为_id，一般_id与对象id一致
                        .setSource(jsonString, XContentType.JSON)  //设置数据json
                        .get();  //发送
        System.out.println("返回结果:"+indexResponse);
    }

}
