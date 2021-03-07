package com.bsfit.suaf.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/6 22:20
 * @verson
 */
public class CuratorTestCreate {
    private static final Logger logger = LoggerFactory.getLogger(CuratorTestCreate.class);
    private static final int TIMEOUT = 3000;
    public CuratorFramework create;

    @Before
    public void init() {
        create = CuratorFrameworkFactory.builder().
                connectString("192.168.2.10:2181,192.168.2.11:2181,192.168.2.12:2181").
                sessionTimeoutMs(TIMEOUT).
                retryPolicy(new RetryOneTime(TIMEOUT)).
                namespace("curatorcreate")./*指定了namespace，会将它作为父节点*/
                build();
        create.start();
    }

    @After
    public void close() {
        if (create != null) {
            create.close();
        }
    }

    @Test
    public void testCreate1() throws Exception{
        create.create().
                withMode(CreateMode.PERSISTENT).
                withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).
                forPath("/node1","node1".getBytes());
        logger.info("结束=====================end====================");
    }
    @Test
    public void testCreate2() throws Exception{
        List<ACL> list = new ArrayList<>();
        Id id1 = new Id("ip","192.168.2.10");
        Id id2 = new Id("ip","192.168.2.11");
        list.add(new ACL(ZooDefs.Perms.ALL,id1));
        list.add(new ACL(ZooDefs.Perms.ALL,id2));

        create.create().
                withMode(CreateMode.PERSISTENT).
                withACL(list).
                forPath("/node2","node2".getBytes());
        logger.info("结束=====================end====================");
    }
    @Test
    public void testCreate3() throws Exception{
        create.create().
                /**循环创建目录树*/
                creatingParentsIfNeeded().
                withMode(CreateMode.PERSISTENT).
                withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).
                forPath("/node3/node3-1","node3-1".getBytes());
        logger.info("结束=====================end====================");
    }
    @Test
    public void testCreate4() throws Exception{
        create.create().
                creatingParentsIfNeeded().
                withMode(CreateMode.PERSISTENT).
                withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).
                inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        System.out.println("curatorEvent.getType() = " + curatorEvent.getType());
                        System.out.println("curatorEvent.getPath() = " + curatorEvent.getPath());
                    }
                }).
                forPath("/node4/node4-1","node4-1".getBytes());
        TimeUnit.SECONDS.sleep(1);
        logger.info("结束=====================end====================");
    }
}
