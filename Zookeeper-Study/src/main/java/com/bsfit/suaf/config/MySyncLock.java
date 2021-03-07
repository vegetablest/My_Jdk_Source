package com.bsfit.suaf.config;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.omg.CORBA.TIMEOUT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/6 18:05
 * @verson
 */
public class MySyncLock implements Watcher {
    private static final Logger logger = LoggerFactory.getLogger(MySyncLock.class);
    private static final String IP = "192.168.2.10";
    private static final int timeOut = 5000;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    private static final String LOCKNAME = "lock";
    private static final String LOCKPATH = "/locks";

    private String lockPath;

    Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent watchedEvent) {
            if (watchedEvent.getType() == Event.EventType.NodeDeleted){
                synchronized (this){
                    notifyAll();
                }
            }
        }
    };
    public MySyncLock() {
        try {
            zooKeeper = new ZooKeeper(IP, timeOut,this);
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成临时有序节点的节点路径
     * [zk: localhost:2181(CONNECTED) 15] create -e -s /locks/lock 1
     * Created /locks/lock0000000000
     * [zk: localhost:2181(CONNECTED) 16]
     * */

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getType() == Event.EventType.None) {
            if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                logger.info("创建连接成功：{},可以向下执行.", watchedEvent.getState());
                countDownLatch.countDown();
            } else if (watchedEvent.getState() == Event.KeeperState.Disconnected) {
                logger.info("zookeeper连接断开：{}", watchedEvent.getState());
            } else if (watchedEvent.getState() == Event.KeeperState.Expired) {
                logger.info("zookeeper连接超时：{}", watchedEvent.getState());
                try {
                    /**
                     * 新建连接
                     * */
                    zooKeeper = new ZooKeeper(IP, timeOut, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (watchedEvent.getState() == Event.KeeperState.AuthFailed) {
                logger.info("zookeeper认证失败：{}", watchedEvent.getState());
            }
        }
    }

    /**
     * 获取锁
     * */
    public void acquiredLock(){
        try {
            createLock();
            attemptLock();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 创建锁节点
     * */
    public void createLock() throws KeeperException, InterruptedException {
        /**判断根节点是否存在*/
        Stat exists = zooKeeper.exists(LOCKPATH, false);
        if (exists == null){
            /**不存在创建*/
            zooKeeper.create(LOCKPATH, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        /**创建临时节点*/
        lockPath = zooKeeper.create(LOCKPATH+"/"+LOCKNAME,new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
        logger.info("创建节点成功{}",lockPath );
    }
    /**
     * 尝试获取锁
     * */
    public void attemptLock() throws KeeperException, InterruptedException {
        /**获取所有子节点*/
        List<String> children = zooKeeper.getChildren(LOCKPATH, false);
        /**排序*/
        Collections.sort(children);
        int indexOf = children.indexOf(lockPath.substring(LOCKPATH.length() + 1));
        if (indexOf == 0){
            logger.info("在集合第一位，获取锁成功");
            return;
        }else{
            /**获取上一个节点位置，然后去监视*/
            String path = children.get(indexOf - 1);
            Stat exists = zooKeeper.exists(LOCKPATH + "/" + path, watcher);
            if (exists == null){
                attemptLock();
            }else {
                synchronized (watcher){
                    watcher.wait();
                }
                attemptLock();
            }
        }
    }
    /**
     * 释放锁
     * */
    public void releaseLock() throws KeeperException, InterruptedException {
        /**删除节点*/
        zooKeeper.delete(this.lockPath,-1);
        zooKeeper.close();
    }

    public static void main(String[] args) throws KeeperException, InterruptedException {
        MySyncLock mySyncLock = new MySyncLock();
        mySyncLock.createLock();
    }
}
