package com.bsfit.suaf.watcher;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/6 16:40
 * @verson
 */
public class ZKWatcherGetChildren implements Watcher {
    private static final Logger logger = LoggerFactory.getLogger(ZKWatcherGetChildren.class);
    private static final String IP = "192.168.2.10";
    private static final int timeOut = 5000;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    @Override
    public void process(WatchedEvent watchedEvent) {
        logger.info("操作的路径：{}，操作的类型{}", watchedEvent.getPath(), watchedEvent.getType());
    }

    @Before
    public void init() {
        try {
            zooKeeper = new ZooKeeper(IP, timeOut, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        logger.info("zookeeper创建连接成功，sessionId=====>{}", zooKeeper.getSessionId());
                        countDownLatch.countDown();
                    }
                    logger.info("操作的路径：{}，操作的类型{}", watchedEvent.getPath(), watchedEvent.getType());
                }
            });
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void closeZK() throws InterruptedException {
        if (zooKeeper != null) {
            zooKeeper.close();
        }
    }

    @Test
    public void testGetChildren1() throws Exception{
        List<String> children = zooKeeper.getChildren("/java", true);
        children.forEach(System.out::println);
        TimeUnit.SECONDS.sleep(20);
    }

    @Test
    public void testGetChildren2() throws Exception{
        List<String> children = zooKeeper.getChildren("/java",this /* new ZKWatcherGetChildren()*/);
        children.forEach(System.out::println);
        TimeUnit.SECONDS.sleep(20);
    }
    @Test
    public void testGetChildren3() throws Exception{
        List<String> children = zooKeeper.getChildren("/java", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                logger.info("操作的路径：{}，操作的类型{}", watchedEvent.getPath(), watchedEvent.getType());
                try {
                    /**
                     * 永动机
                     * */
                    zooKeeper.getChildren("/java", this);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        children.forEach(System.out::println);
        TimeUnit.SECONDS.sleep(20);
    }

}
