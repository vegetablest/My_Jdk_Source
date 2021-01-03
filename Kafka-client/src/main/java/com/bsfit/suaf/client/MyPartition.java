package com.bsfit.suaf.client;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @author bangsun
 */
public class MyPartition implements Partitioner {

    /**
     * 自定义分区设置，默认如果不指定，是按照key的hash
     */
    @Override
    public int partition(String topic, Object key, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        //写死不合适，怎么自定义
        //return key.toString().hashCode() % 3;
        /**
         * 获取分区数，取模
         * */
        Integer integer = cluster.partitionCountForTopic(topic);
        return key.toString().hashCode() % integer;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
