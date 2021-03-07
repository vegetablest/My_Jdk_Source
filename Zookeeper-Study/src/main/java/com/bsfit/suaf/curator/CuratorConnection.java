package com.bsfit.suaf.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.retry.RetryUntilElapsed;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/6 21:41
 * @verson
 */
public class CuratorConnection {

    public static void main(String[] args) {
        /**
         * session重连策略
         * 1.三秒之后重连一次
         * 2.重连3次，每次间隔三秒
         * 3.最大等待重连30秒，每次隔3秒
         * 4.每隔1秒重连一次，但是第二次是2秒，第三次是三秒，一共就三次
         * */
        RetryPolicy policy1= new RetryOneTime(3000);
        RetryPolicy policy2=  new RetryNTimes(3,3000);
        RetryPolicy policy3=  new RetryUntilElapsed(30000,3000);
        RetryPolicy policy4=  new ExponentialBackoffRetry(1000,3);


        CuratorFramework create = CuratorFrameworkFactory.builder().
                /**IP和端口*/
                        connectString("192.168.2.10:2181,192.168.2.11:2181,192.168.2.12:2181").
                /**session超时事件*/
                        sessionTimeoutMs(5000).
                /**断开重连对象*/
                        retryPolicy(new RetryOneTime(3000)).
                /**命名空间*/
                        namespace("create").build();

        create.start();
        System.out.println("create.isStarted() ==================> " + create.isStarted());
        create.close();

    }
}
