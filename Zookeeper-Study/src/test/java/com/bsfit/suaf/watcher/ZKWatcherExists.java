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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/6 15:47
 * @verson
 */
public class ZKWatcherExists implements Watcher {

    private static final Logger logger = LoggerFactory.getLogger(ZKWatcherExists.class);
    private static final String IP = "192.168.2.10";
    private static final int timeOut = 5000;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            logger.info("zookeeper创建连接成功，sessionId=====>{}", zooKeeper.getSessionId());
            countDownLatch.countDown();
        }
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
    public void testExists() throws Exception {
        /**
         * 1.监控路径
         * 2.是否使用watcher
         * */
        zooKeeper.exists("/watcher1", true);
        TimeUnit.SECONDS.sleep(30);
        logger.info("结束");
    }

    @Test
    public void testExists2() throws Exception {
        /**
         * 1.监控路径
         * 2.使用自定义的watcher
         * */
        zooKeeper.exists("/watcher1", this /*new ZKWatcherExists()*/);
        TimeUnit.SECONDS.sleep(30);
        logger.info("结束");
    }

    @Test
    public void testExists3() throws Exception {
        /**
         * 1.监控路径
         * 2.使用自定义的watcher
         * 因为watcher是一次性的，所以一次之和就没了，但是可以在process中再注册监听器
         * */
        zooKeeper.exists("/watcher1", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                logger.info("自定义watcher{}", watchedEvent);
                logger.info("操作的路径：{}，操作的类型", watchedEvent.getPath(), watchedEvent.getType());
                try {
                    zooKeeper.exists("/watcher1",this);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        TimeUnit.SECONDS.sleep(30);
        logger.info("结束");
    }


}