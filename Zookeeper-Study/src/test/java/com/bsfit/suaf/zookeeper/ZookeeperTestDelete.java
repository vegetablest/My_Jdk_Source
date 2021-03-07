package com.bsfit.suaf.zookeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/6 14:23
 * @verson
 */
public class ZookeeperTestDelete {
    private static final Logger logger = LoggerFactory.getLogger(ZookeeperTestDelete.class);
    private ZooKeeper zooKeeper = null;

    @Before
    public void initZoo() throws IOException {
        try {
            /**
             * 新建一个计数器对象，用来阻塞主线程，等zookeeper连接创建号之后再释放
             * */
            CountDownLatch countDownLatch = new CountDownLatch(1);
            /**
             * 参数1.服务器连接地址，超时时间，实现watcher接口的类
             * */
            zooKeeper = new ZooKeeper("192.168.2.10", 5000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {

                    if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        /**
                         * 连接成功，使用计数器对象，通知其向下执行
                         * */
                        logger.info("zookeeper create conection success,status===>:{}", watchedEvent.getState());
                        countDownLatch.countDown();
                    }
                }
            });
            /**
             * 主线程阻塞等待创建成功
             * */
            countDownLatch.await();
            logger.info("zookeeper conection object:{}", zooKeeper.getSessionId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void closeZoo() throws InterruptedException {
        if (zooKeeper != null){
            zooKeeper.close();
        }
    }

    @Test
    public void testDeleteSync() throws Exception{
        /**
         * 1.删除数据的路径
         * 2.删除时候版本号是否作为
         * */
        zooKeeper.delete("/delete/node1",-1);
    }
    @Test
    public void testDeleteAsync() throws Exception{
        zooKeeper.delete("/delete/node1", -1, new AsyncCallback.VoidCallback() {
            @Override
            public void processResult(int i, String s, Object o) {
                logger.info("删除结果：{}", i);
                logger.info("删除路径：{}", s);
                logger.info("上下文信息：{}", o);
            }
        },"i am delete");
        TimeUnit.SECONDS.sleep(1);
    }
}
