package com.bsfit.suaf.config;

import org.apache.zookeeper.KeeperException;

import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/6 18:44
 * @verson
 */
public class TestZooLock {

    private void sell(){
        try {
            /**等会，假装复杂逻辑*/
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("卖票结束");
    }

    private void sellTickWithLock() throws KeeperException, InterruptedException {
        MySyncLock mySyncLock = new MySyncLock();
        mySyncLock.acquiredLock();
        sell();
        mySyncLock.releaseLock();
    }
    public static void main(String[] args) throws KeeperException, InterruptedException {
        TestZooLock testZooLock = new TestZooLock();
        for (int i = 0; i < 10; i++) {
            testZooLock.sellTickWithLock();
        }
    }
}
