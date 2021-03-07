package com.bsfit.suaf.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.RetryOneTime;
import org.apache.log4j.Logger;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/7 9:15
 * @verson
 */
public class CuratorTestExesits {
    Logger logger = Logger.getLogger(CuratorTestExesits.class);
    private static final int TIMEOUT = 3000;
    public CuratorFramework exists;

    @Before
    public void init() {
        exists = CuratorFrameworkFactory.builder().
                connectString("192.168.2.10:2181,192.168.2.11:2181,192.168.2.12:2181").
                sessionTimeoutMs(TIMEOUT).
                retryPolicy(new RetryOneTime(TIMEOUT)).
                namespace("curatorcreate")./*指定了namespace，会将它作为父节点*/
                build();
        exists.start();
    }

    @After
    public void close() {
        if (exists != null) {
            exists.close();
        }
    }

    @Test
    public void testExists1() throws Exception{
        Stat stat = exists.checkExists().forPath("/node3");
        logger.info(String.format("返回结果是节点相关属性：%s",stat ));
    }
    @Test
    public void testExists2() throws Exception{
        exists.checkExists().inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                logger.info(String.format("返回结果：%s",curatorEvent.getStat()) );
            }
        }).forPath("/node3");
        TimeUnit.SECONDS.sleep(1);
    }
}
