package com.bsfit.suaf.client;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
/**
 * 自定义拦截器
 * @author bangsun
 * */
public class MyInterceptor implements ProducerInterceptor<String,String> {

    int success;
    int error;
    /**
     * 配置
     * */
    @Override
    public void configure(Map<String, ?> map) {

    }
    /**
     * 消息发送前
     * */
    @Override
    public ProducerRecord onSend(ProducerRecord<String,String> producerRecord) {
        //取出数据
        String value = producerRecord.value();
        //修改数据
        String newValue = value+"拦截器处理之后的数据";
        return new ProducerRecord(producerRecord.topic(),producerRecord.partition(),producerRecord.key(),newValue);
    }

    /**
     * 消息发送成功与否信息
     * */
    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

        if (recordMetadata != null){
            success++;
        }else {
            error++;
        }
    }

    /**
     * 关闭拦截器资源，主要作用是清理资源
     * */
    @Override
    public void close() {
        System.out.println("成功条数"+success);
        System.out.println("失败次数"+error);
    }
}
