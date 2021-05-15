package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * <br>
 * 手写轮询负载均衡记得关闭restTemplate上的轮询注解
 * @author bangsun
 * @data 2021/5/14 14:45
 * @verson
 */
public interface LoadBalancer {

    /**返回一个集群实例*/
    ServiceInstance getInstances(List<ServiceInstance> serviceInstances);
}
