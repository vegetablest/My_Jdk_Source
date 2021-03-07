package com.bsfit.suaf.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/6 20:55
 * @verson
 */
public class ZookeeperConectionCluster {
    private static final Logger logger = LoggerFactory.getLogger(ZookeeperConectionCluster.class);
    public static void main(String[] args) {
        try {
            /**新建一个计数器对象，用来阻塞主线程，等zookeeper连接创建号之后再释放*/
            CountDownLatch countDownLatch = new CountDownLatch(1);
            /**参数1.服务器连接地址，超时时间，实现watcher接口的类*/
            ZooKeeper zooKeeper = new ZooKeeper("192.168.2.10:2181,192.168.2.11:2181,192.168.2.12:2181", 5000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        /**连接成功，使用计数器对象，通知其向下执行*/
                        logger.info("zookeeper create conection success,status===>:{}", watchedEvent.getState());
                        countDownLatch.countDown();
                    }
                }
            });
            /**主线程阻塞等待创建成功*/
            countDownLatch.await();
            logger.info("zookeeper conection object:{}", zooKeeper.getSessionId());
            zooKeeper.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
