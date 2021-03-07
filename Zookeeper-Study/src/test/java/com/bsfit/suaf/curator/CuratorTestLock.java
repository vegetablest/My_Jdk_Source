package com.bsfit.suaf.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.RetryOneTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/7 10:01
 * @verson
 */
public class CuratorTestLock {
    private static final int TIMEOUT = 3000;
    public CuratorFramework create;

    @Before
    public void init() {
        create = CuratorFrameworkFactory.builder().
                connectString("192.168.2.10:2181,192.168.2.11:2181,192.168.2.12:2181").
                sessionTimeoutMs(TIMEOUT).
                retryPolicy(new RetryOneTime(TIMEOUT)).
                namespace("curatorcreate")./*指定了namespace，会将它作为父节点*/
                build();
        create.start();
    }

    @After
    public void close() {
        if (create != null) {
            create.close();
        }
    }

    @Test
    public void testNoShareLock() throws Exception{
        /**
         * 排它锁
         * 1.连接对象
         * 2.节点路径
         * */
        InterProcessLock interProcessLock = new InterProcessMutex(create,"/lock1");
        System.out.println("获取锁");
        interProcessLock.acquire();
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(i);
        }
        interProcessLock.release();
        System.out.println("释放锁");

    }
    @Test
    public void testShareLock() throws Exception{
        /**
         * 读写锁
         * */
        InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(create,"/lock1");
        InterProcessMutex readLock = interProcessReadWriteLock.readLock();
        System.out.println("获取锁");
        readLock.acquire();
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(i);
        }
        readLock.release();
        System.out.println("释放锁");
    }
    @Test
    public void testShareLock3() throws Exception{
        /**
         * 读写锁
         * */
        InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(create,"/lock1");
        InterProcessMutex writeLock = interProcessReadWriteLock.writeLock();
        System.out.println("获取锁");
        writeLock.acquire();
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(i);
        }
        writeLock.release();
        System.out.println("释放锁");
    }
}
