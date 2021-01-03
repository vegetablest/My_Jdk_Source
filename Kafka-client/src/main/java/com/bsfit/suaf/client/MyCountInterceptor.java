package com.bsfit.suaf.client;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author bangsun
 */
public class MyCountInterceptor implements ProducerInterceptor<String,String> {

    int success;
    int error;

    @Override
    public void configure(Map<String, ?> map) {

    }

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

        if (recordMetadata != null){
            success++;
        }else {
            error++;
        }
    }

    @Override
    public void close() {
        System.out.println("成功条数"+success);
        System.out.println("失败次数"+error);
    }


}
