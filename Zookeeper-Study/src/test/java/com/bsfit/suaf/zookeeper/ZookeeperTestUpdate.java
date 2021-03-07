package com.bsfit.suaf.zookeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/6 14:10
 * @verson
 */
public class ZookeeperTestUpdate {
    private static final Logger logger = LoggerFactory.getLogger(ZookeeperTestUpdate.class);

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
        if (zooKeeper != null) {
            zooKeeper.close();
        }
    }

    @Test
    public void testSetSync() throws Exception {
        /**
         * 1.节点的路径
         * 2.修改的数据
         * 3.数据的版本号，-1表示版本号不参与更新
         * */
        Stat stat = zooKeeper.setData("/set/node1", "node1111".getBytes(), -1);
        logger.info("set/node1更新成功，返回版本信息{}", stat.getVersion());
    }

    @Test
    public void testSetAsync() throws Exception {
        /**
         * 1.节点的路径
         * 2.修改的数据
         * 3.数据的版本号，-1表示版本号不参与更新
         * 4.回调
         * */
        zooKeeper.setData("/set/node1", "node11111".getBytes(), -1, new AsyncCallback.StatCallback() {
            @Override
            public void processResult(int i, String s, Object o, Stat stat) {
                logger.info("修改结果：{}", i);
                logger.info("修改路径：{}", s);
                logger.info("上下文信息：{}", o);
                logger.info("版本信息:{}", stat.getVersion());
            }
        }, "I am set");
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void testGet() throws Exception{
        /**
         * 1.读取节点路径
         * 2.
         * 3.节点的状态信息
         * */
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/get/node1", false, stat);
        logger.info("获取数据{}",new String(data));
    }
    @Test
    public void testGetAsync() throws Exception{
        /**
         * 1.读取节点路径
         * 2.
         * 3.节点的状态信息
         * */
        Stat stat = new Stat();
        zooKeeper.getData("/get/node1", false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
                logger.info("操作状态{}",i );
                logger.info("操作路径{}", o);
                logger.info("返回结果{}", bytes);
                logger.info("stat状态{}", stat);
            }
        },"i am get");
        TimeUnit.SECONDS.sleep(1);

    }

    @Test
    public void testGetChird() throws Exception{
        /**
         * 1.读取节点路径
         * 2.
         * 3.节点的状态信息
         * */
        Stat stat = new Stat();
        List<String> children = zooKeeper.getChildren("/get", false, stat);
        logger.info("输出子节点[{}]",children );

    }
    @Test
    public void testGetChirdAsync() throws Exception{
        /**
         * 1.读取节点路径
         * 2.
         * 3.节点的状态信息
         * */
        zooKeeper.getChildren("/get", false, new AsyncCallback.ChildrenCallback() {
            @Override
            public void processResult(int i, String s, Object o, List<String> list) {
                logger.info("操作结果{}", i);
                logger.info("操作路径{}", s);
                logger.info("上下文{}", o);
                logger.info("返回子节点{}", list);
            }
        },"i am get chirend");
        TimeUnit.SECONDS.sleep(1);

    }
}
