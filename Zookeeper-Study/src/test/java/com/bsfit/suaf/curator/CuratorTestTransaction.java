package com.bsfit.suaf.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.CreateMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/7 9:47
 * @verson
 */
public class CuratorTestTransaction {
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
    public void testTrans() throws Exception {
        create.create().withMode(CreateMode.PERSISTENT).forPath("/node2", "node2".getBytes());
        /**没有node5会跑错，但是查看发现node2竟然创建成功了，显然不是原子操作*/
        create.setData().forPath("/node5", "node5".getBytes());
    }

    @Test
    public void testTrans2() throws Exception {
        create.inTransaction().
                create().withMode(CreateMode.PERSISTENT).forPath("/node4", "node4".getBytes()).
                and()
                /**没有node5会抛错，但是查看node4没有创建成功，显然原子性操作*/
                .setData().forPath("/node5", "node5".getBytes()).
                and().
                commit();
    }
}
