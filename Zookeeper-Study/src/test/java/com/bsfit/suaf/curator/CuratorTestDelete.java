package com.bsfit.suaf.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.RetryOneTime;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/6 23:00
 * @verson
 */
public class CuratorTestDelete {
    Logger logger = Logger.getLogger(CuratorTestDelete.class);
    private static final int TIMEOUT = 3000;
    public CuratorFramework delete;

    @Before
    public void init() {
        delete = CuratorFrameworkFactory.builder().
                connectString("192.168.2.10:2181,192.168.2.11:2181,192.168.2.12:2181").
                sessionTimeoutMs(TIMEOUT).
                retryPolicy(new RetryOneTime(TIMEOUT)).
                namespace("curatorcreate")./*指定了namespace，会将它作为父节点*/
                build();
        delete.start();
    }

    @After
    public void close() {
        if (delete != null) {
            delete.close();
        }
    }

    @Test
    public void testDelete1() throws Exception{
        delete.delete().forPath("/node1");
        delete.delete().withVersion(-1).forPath("/node2");
    }
    @Test
    public void testDelete2() throws Exception{
        delete.delete().deletingChildrenIfNeeded()
                .withVersion(-1).
                forPath("/node3");
    }
    @Test
    public void testDelete4() throws Exception{
        delete.delete()
                .deletingChildrenIfNeeded()
                .withVersion(-1)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        System.out.println("curatorEvent.getType() = " + curatorEvent.getType());
                        System.out.println("curatorEvent.getPath() = " + curatorEvent.getPath());
                        logger.info(String.format("haha%s",curatorEvent.getPath()));
                    }
                })
                .forPath("/node4");
        TimeUnit.SECONDS.sleep(1);
    }
}
