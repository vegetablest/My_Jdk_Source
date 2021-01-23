package test.juctest;

import jdk.internal.org.objectweb.asm.TypeReference;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bangsun
 */
public class JUC20RentLock {
    static Object object = new Object();

    public static void main(String[] args) {
        /**
         *  java可重用锁，可重入锁:可重复可递归调用的锁，在外层使用锁之后，
         *  在内层仍然可以使用，并且不发生死锁，这样的锁就叫做可重入锁。
         *  在一个synchronized 修饰的方法或代码块的内部调用本类的其他synchronized修饰的方法或代码块时，
         *  是永远可以得到锁的，所以synchhronized是可重用锁，reentrantLock也是可重用锁
         * */
        m1();
        new JUC20RentLock().m2();
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("lock的外层锁");
                lock.lock();
                try {
                    System.out.println("lock的中层锁");
                    lock.lock();
                    try {
                        System.out.println("lock的内层锁");
                    } finally {
                        lock.unlock();
                    }
                } finally {
//                    随便注掉一行，第二个线程始终无法获得锁，导致死锁
                    lock.unlock();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"T1").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("T2调用开始");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"T2").start();
    }

    /**
     * 同步代码块
     */
    public static void m1() {
        synchronized (object) {
            System.out.println("外层锁");
            synchronized (object) {
                System.out.println("中层锁");
                synchronized (object) {
                    System.out.println("内层锁");
                }
            }
        }
    }

    /**
     * 同步方法
     */
    public synchronized void m2() {
        System.out.println("外层方法");
        m3();
    }

    public synchronized void m3() {
        System.out.println("中层方法");

        m4();
    }

    public synchronized void m4() {
        System.out.println("内层方法");
    }

}
