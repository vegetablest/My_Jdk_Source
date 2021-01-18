package test.juctest;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多个线程同读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。但是
 * 如果有一个线程想去写共享资源来，就不应该再有其它线程可以对该资源进行读或写
 * 小总结:
 * 读-读能共存
 * 读-写不能共存
 * 写-写不能共存
 * @author bangsun
 */
@SuppressWarnings("all")
public class JUC14ReadWriteLock {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    myCache.put(String.valueOf(finalI),String.valueOf(finalI));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"写入线程"+i).start();
        }
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    myCache.get(String.valueOf(finalI));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"读取线程"+i).start();
        }
    }
}

class MyCache{

    private volatile Map<String,Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object o) throws InterruptedException {

        readWriteLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName()+"\t"+"开始写入");
            TimeUnit.SECONDS.sleep(1);
            map.put(key,o);
            System.out.println(Thread.currentThread().getName()+"\t"+"写入完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) throws InterruptedException {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t"+"开始读取");
            TimeUnit.SECONDS.sleep(1);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t"+"读取完成,得到"+o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

}
//class MyCache{
//
//    private volatile Map<String,Object> map = new HashMap<>();
//
//    public void put(String key,Object o) throws InterruptedException {
//        System.out.println(Thread.currentThread().getName()+"\t"+"开始写入");
//        TimeUnit.SECONDS.sleep(3);
//        map.put(key,o);
//        System.out.println(Thread.currentThread().getName()+"\t"+"写入完成");
//    }
//
//    public void get(String key) throws InterruptedException {
//        System.out.println(Thread.currentThread().getName()+"\t"+"开始读取");
//        TimeUnit.SECONDS.sleep(3);
//        Object o = map.get(key);
//        System.out.println(Thread.currentThread().getName()+"\t"+"读取完成,得到"+o);
//    }
//
//}