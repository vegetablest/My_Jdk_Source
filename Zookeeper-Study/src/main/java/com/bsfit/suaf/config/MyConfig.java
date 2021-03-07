package com.bsfit.suaf.config;

import com.bsfit.suaf.watcher.ZKConnectionWatcher;
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
 *
 * @author bangsun
 * @data 2021/3/6 16:53
 * @verson
 */
public class MyConfig implements Watcher {
    private static final Logger logger = LoggerFactory.getLogger(MyConfig.class);
    private static final String IP = "192.168.2.10";
    private static final int timeOut = 5000;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    /**
     * 用于本地化存储信息
     * */
    private String url;
    private String username;
    private String password;

    public MyConfig() {
        initValue();
    }

    public void initValue(){
        try {
            zooKeeper = new ZooKeeper(IP, timeOut, this);
            /**
             * 等待连接创建
             * */
            countDownLatch.await();
            TimeUnit.SECONDS.sleep(1);
            this.url = new String(zooKeeper.getData("/myconfig/url",true,null));
            this.username = new String(zooKeeper.getData("/myconfig/username",true,null));
            this.password = new String(zooKeeper.getData("/myconfig/password",true,null));
        } catch (Exception e) {
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
                    zooKeeper = new ZooKeeper(IP, timeOut, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (watchedEvent.getState() == Event.KeeperState.AuthFailed) {
                logger.info("zookeeper认证失败：{}", watchedEvent.getState());
            }
        }else if (watchedEvent.getType() == Event.EventType.NodeDataChanged){
            /**
             * 如果节点信息发生改变，我们立刻重新初始化
             * */
            initValue();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyConfig myConfig = new MyConfig();
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("myConfig.getUrl() = " + myConfig.getUrl());
            System.out.println("myConfig.getUsername() = " + myConfig.getUsername());
            System.out.println("myConfig.getPassword() = " + myConfig.getPassword());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


}
