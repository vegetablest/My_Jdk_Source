package com.atguigu.springcloud.lb;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/5/14 14:43
 * @verson
 */
@Slf4j
@Component
public class MyLoadBalancer implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
            /*CAS,避免高并发下已经有人改了*/
        }while (! this.atomicInteger.compareAndSet(current,next));

        log.info("*****************第几次访问，次数next:"+next);
        return next;
    }

    /**
     *  list = discoveryClient.getInstances();
     *  负载均衡算法：rest接口第几次请求 % 服务器集群总是 = 实际调用服务器位置下标
     *  每次服务重启rest接口从零开始计算
     * */
    @Override
    public ServiceInstance getInstances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
