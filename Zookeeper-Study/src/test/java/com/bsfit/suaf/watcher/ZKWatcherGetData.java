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
 * @data 2021/3/6 16:17
 * @verson
 */
public class ZKWatcherGetData implements Watcher {
    private static final Logger logger = LoggerFactory.getLogger(ZKWatcherGetData.class);
    private static final String IP = "192.168.2.10";
    private static final int timeOut = 5000;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    @Override
    public void process(WatchedEvent watchedEvent) {
        countDownLatch.countDown();
        logger.info("操作的路径：{}，操作的类型{}", watchedEvent.getPath(), watchedEvent.getType());
    }

    @Before
    public void init() {
        try {
            zooKeeper = new ZooKeeper(IP, timeOut, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    logger.info("zookeeper创建连接成功，sessionId=====>{}", zooKeeper.getSessionId());
                    countDownLatch.countDown();
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
    public void testGetData1() throws Exception{

        byte[] data = zooKeeper.getData("/get/node1", true, null);
        logger.info("获取数据：{}",new String(data));
        TimeUnit.SECONDS.sleep(20);
    }
    @Test
    public void testGetData2() throws Exception{
        byte[] data = zooKeeper.getData("/get/node1", this /*new ZKWatcherGetData()*/, null);
        logger.info("获取数据：{}",new String(data));
        TimeUnit.SECONDS.sleep(20);
    }
    @Test
    public void testGetData3() throws Exception{
        byte[] data = zooKeeper.getData("/get/node1", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                logger.info("操作的路径：{}，操作的类型{}", watchedEvent.getPath(), watchedEvent.getType());
                try {
                    /**
                     * 永动机
                     * */
                    byte[] data = zooKeeper.getData("/get/node1",this,null);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, null);
        logger.info("获取数据：{}",new String(data));
        TimeUnit.SECONDS.sleep(20);
    }
}
