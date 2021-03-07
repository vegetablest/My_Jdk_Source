package com.bsfit.suaf.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 * ZK的连接状态
 * @author bangsun
 * @data 2021/3/6 15:23
 * @verson
 */
public class ZKConnectionWatcher implements Watcher {

    private static final Logger logger = LoggerFactory.getLogger(ZKConnectionWatcher.class);
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) {
        try {
            zooKeeper = new ZooKeeper("192.168.2.10", 5000, new ZKConnectionWatcher());
            logger.info("会话id{}", zooKeeper.getSessionId());
            countDownLatch.await();
            TimeUnit.SECONDS.sleep(1);
            zooKeeper.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

        if (watchedEvent.getType() == Event.EventType.None) {
            if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                logger.info("创建连接成功：{}", watchedEvent.getState());
                countDownLatch.countDown();
            } else if (watchedEvent.getState() == Event.KeeperState.Disconnected) {
                logger.info("zookeeper连接断开：{}", watchedEvent.getState());
            } else if (watchedEvent.getState() == Event.KeeperState.Expired) {
                logger.info("zookeeper连接超时：{}", watchedEvent.getState());
                try {
                    /**
                     * 新建连接
                     * */
                    zooKeeper = new ZooKeeper("192.168.2.10", 5000, new ZKConnectionWatcher());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (watchedEvent.getState() == Event.KeeperState.AuthFailed) {
                logger.info("zookeeper认证失败：{}", watchedEvent.getState());
            }
        }
    }
}
