package com.bsfit.suaf.client;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

/**
 * @author bangsun
 */
public class SimpleCoustomer {

    public static void main(String[] args) throws IOException, InterruptedException {

        Properties properties = new Properties();
        /*
         * key.deserializer  的反序列化配置
         * value.deserializer  的反序列化配置
         * enable.auto.commit  true false  自动提交配置
         * bootstrap.servers  的配置，连接的主机
         * group.id消费者组test
         * auto.offset.reset   配置从哪开始拉数据 earliest
         * */
//        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.setProperty("bootstrap.servers", "192.168.2.10:9092");
//        properties.setProperty("enable.auto.commit", true);
//        properties.setProperty("group.id", "test");
//        properties.setProperty("auto.offset.reset", "earliest");
        properties.load(SimpleCoustomer.class.getClassLoader().getResourceAsStream("consumer.properties"));
        //实例化consumer
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        //使用它接受消息,先订阅，参数是一个集合
        kafkaConsumer.subscribe(Collections.singleton("first01"));
        //从评阅的topic中拉数据，2秒拉不到算这次拉去失败
        while (true){
            ConsumerRecords<String, String> poll = kafkaConsumer.poll(2000);
            if (poll == null){
                Thread.sleep(1000);
            }
            for (ConsumerRecord<String, String> consumerRecord : poll) {
                System.out.println("consumerRecord = " + consumerRecord);
            }
            //避免重复消费，最好关闭自动提交，手动提交，但是还可能你消费完了，挂了导致offset并没有提交，你再消费
            //依旧会存在重复消费
//            kafkaConsumer.commitSync();
//            kafkaConsumer.commitAsync();
        }
        //关闭consumer
//        kafkaConsumer.close();
    }
}
