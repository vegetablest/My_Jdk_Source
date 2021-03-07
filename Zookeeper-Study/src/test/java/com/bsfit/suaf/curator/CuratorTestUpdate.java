package com.bsfit.suaf.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.RetryOneTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/6 22:46
 * @verson
 */
public class CuratorTestUpdate {
    private static final Logger logger = LoggerFactory.getLogger(CuratorTestUpdate.class);
    private static final int TIMEOUT = 3000;
    public CuratorFramework update;

    @Before
    public void init() {
        update = CuratorFrameworkFactory.builder().
                connectString("192.168.2.10:2181,192.168.2.11:2181,192.168.2.12:2181").
                sessionTimeoutMs(TIMEOUT).
                retryPolicy(new RetryOneTime(TIMEOUT)).
                namespace("curatorcreate")./*指定了namespace，会将它作为父节点*/
                build();
        update.start();
    }

    @After
    public void close() {
        if (update != null) {
            update.close();
        }
    }

    @Test
    public void testSet() throws Exception {
        update.setData().withVersion(-1).forPath("/node1", "node11".getBytes());
        byte[] bytes = update.getData().forPath("/node1");
        logger.info("更改成功===》{}", new String(bytes));
    }

    @Test
    public void testSet2() throws Exception {
        update.setData().withVersion(-1).
                inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        logger.info("操作类型{}", curatorEvent.getType());
                        logger.info("操作路径{}", curatorEvent.getPath());
                    }
                })
                .forPath("/node1", "node111".getBytes());
        byte[] bytes = update.getData().forPath("/node1");
        logger.info("更改成功===》{}", new String(bytes));
    }

}
