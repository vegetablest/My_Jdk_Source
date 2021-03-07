package com.bsfit.suaf.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.RetryOneTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/7 9:22
 * @verson
 */
public class CuratorTestListener {
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
    public void testListener1() throws Exception{
        /**
         * 1.连接对象
         * 2.监视路径
         * */
        NodeCache nodeCache = new NodeCache(create, "/node1");
        nodeCache.start();
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println(nodeCache.getCurrentData().getPath());
            }
        });
        TimeUnit.SECONDS.sleep(10);
        nodeCache.close();
    }
}
