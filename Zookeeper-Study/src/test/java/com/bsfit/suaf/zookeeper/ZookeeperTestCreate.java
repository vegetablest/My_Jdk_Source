package com.bsfit.suaf.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/6 13:28
 * @verson
 */
public class ZookeeperTestCreate {
    private static final Logger logger = LoggerFactory.getLogger(ZookeeperTestCreate.class);
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
    public void testCreateSync() throws Exception {
        /**
         * 1.创建的路径
         * 2.创建的值
         * 3.ACL权限列表
         * 4.节点类型
         * [zk: localhost:2181(CONNECTED) 18] getAcl /java/node1
         * 'world,'anyone
         * : cdrwa
         * [zk: localhost:2181(CONNECTED) 17] get /java/node1
         * node1
         * */
        zooKeeper.create("/java/node1","node1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        logger.info("node create success===================>/java/node1");
    }
    @Test
    public void testCreateSyncAcl() throws Exception {
        /**
         * 1.创建的路径
         * 2.创建的值
         * 3.ACL权限列表
         * 4.节点类型
         * [zk: localhost:2181(CONNECTED) 18] getAcl /java/node2
         * 'world,'anyone
         * : r
         * [zk: localhost:2181(CONNECTED) 17] get /java/node2
         * node2
         * */
        zooKeeper.create("/java/node2","node2".getBytes(), ZooDefs.Ids.READ_ACL_UNSAFE, CreateMode.PERSISTENT);
        logger.info("node create success===================>/java/node2");
    }
    @Test
    public void testCreateSyncDenfined() throws Exception {
        /**
         * 1.创建的路径
         * 2.创建的值
         * 3.ACL权限列表
         * 4.节点类型
         * [zk: localhost:2181(CONNECTED) 22] getAcl /java/node3
         * 'world,'anyone
         * : r
         * 'world,'anyone
         * : w
         * */
        List<ACL> acls= new ArrayList<>();
        Id id = new Id("world", "anyone");
        acls.add(new ACL(ZooDefs.Perms.READ,id));
        acls.add(new ACL(ZooDefs.Perms.WRITE,id));

        zooKeeper.create("/java/node3","node3".getBytes(), acls, CreateMode.PERSISTENT);
        logger.info("node create success===================>/java/node3");
    }
    @Test
    public void testCreateSyncDenfined1() throws Exception {
        /**
         * 1.创建的路径
         * 2.创建的值
         * 3.ACL权限列表
         * 4.节点类型
         * [zk: localhost:2181(CONNECTED) 26] getAcl /java/node4
         * 'ip,'192.168.2.10
         * : cdrwa
         * */
        List<ACL> acls= new ArrayList<>();
        Id id = new Id("ip", "192.168.2.10");
        acls.add(new ACL(ZooDefs.Perms.ALL,id));
        zooKeeper.create("/java/node4","node4".getBytes(), acls, CreateMode.PERSISTENT);
        logger.info("node create success===================>/java/node4");
    }
    @Test
    public void testCreateSyncPersistentSequential() throws Exception {
        String s = zooKeeper.create("/java/node5", "node5".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        logger.info("persisent sequential node create success===================>/java/node5,返回结果{}",s);
    }
    @Test
    public void testCreateSyncEphemeral() throws Exception {
        /**
         * CreateMode.EPHEMERAL
         * 创建了一个临时节点，session关闭即消失
         * */
        String s = zooKeeper.create("/java/node6", "node6".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        logger.info("persisent ephemeral node create success===================>/java/node6,返回结果：{}",s);
    }



    @Test
    public void testCreateAsync() throws Exception {
        /**
         * 1.创建的路径
         * 2.创建的值
         * 3.ACL权限列表
         * 4.节点类型
         * 5.异步创建节点
         * */
        zooKeeper.create("/java/node7", "node7".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new AsyncCallback.StringCallback() {
            @Override
            public void processResult(int i, String s, Object o, String s1) {
                logger.info("i是{}，代表创建成功",i );
                logger.info("s是{}，代表创建的路径",s);
                logger.info("o是{}，代表回调结果",o);
                logger.info("s1是{}，代表创建的上下文信息",s1);
            }
        },"I am Create Node7");
        TimeUnit.SECONDS.sleep(1);
        logger.info("node create success===================>/java/node7");
    }
}
