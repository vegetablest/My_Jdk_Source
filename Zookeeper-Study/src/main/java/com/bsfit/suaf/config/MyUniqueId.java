package com.bsfit.suaf.config;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/6 17:42
 * @verson
 */
public class MyUniqueId implements Watcher {
    private static final Logger logger = LoggerFactory.getLogger(MyUniqueId.class);
    private static final String IP = "192.168.2.10";
    private static final int timeOut = 5000;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;
    /**
     * 生成临时有序节点的节点路径
     * */
    private String defaultPath = "/uniquecode";

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
                    zooKeeper = new ZooKeeper(IP, timeOut, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (watchedEvent.getState() == Event.KeeperState.AuthFailed) {
                logger.info("zookeeper认证失败：{}", watchedEvent.getState());
            }
        }
    }

    public MyUniqueId() {
        try {
            zooKeeper= new ZooKeeper(IP,timeOut,this);
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getUniquerId(){
        String path = "";
        try {
            path = zooKeeper.create(defaultPath, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        }catch (Exception e){
            e.printStackTrace();
        }
        return path.substring(11);
    }
    public static void main(String[] args) {
        MyUniqueId myUniqueId = new MyUniqueId();
        for (int i = 0; i < 10; i++) {
            String code = myUniqueId.getUniquerId();
            System.out.println(code);
        }

    }

}
