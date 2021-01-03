package com.bsfit.suaf.client;

import org.apache.kafka.clients.producer.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 消息生产者
 *
 * @author bangsun
 */
public class SimpleProducer {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        Properties properties = new Properties();
        /*
         * key.serializer  的序列化配置，实现了serialization接口的字符串序列化方式
         * value.serializer  的序列化配置，实现了serialization接口的字符串序列化方式
         * acks  的配置，0 1 -1 all
         * bootstrap.servers  的配置，连接的主机
         * */
//        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        properties.setProperty("acks", "all");
//        properties.setProperty("bootstrap.servers", "192.168.2.10:9092");
        //添加分区器
//        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.bsfit.suaf.client.MyPartition");
        //添加拦截器

        properties.load(SimpleProducer.class.getClassLoader().getResourceAsStream("kafka.properties"));
        properties.setProperty(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, "com.bsfit.suaf.client.MyInterceptor");
        //1.实例化Kafka集群,传配置文件或者map
        KafkaProducer<String, String> kafkaProducer
                = new KafkaProducer<>(properties);

        //2.用集群对象发送数据ProducerRecord,且给他回调函数，不给也行
        for (int i = 0; i < 10; i++) {
            //发送的原FutureRecordMetadata数据,因为key是指定死的，所以hash一样，它放到的分区也一样
            Future<RecordMetadata> future = kafkaProducer.send(new ProducerRecord<String, String>(
                    "first01",
                    Integer.toString(1),
                    "Value" + i
            ), (recordMetadata, exception) -> {
                if (exception == null) {
                    System.out.println("metadata" + recordMetadata);
                }
            });
            //加上这两行就变成同步了，发一条等一条
            //RecordMetadata recordMetadata = future.get();
            //System.out.println(recordMetadata);
            System.out.println("发送第"+i+"条成功:");
        }
        //3.关闭资源
        kafkaProducer.close();
    }
}
