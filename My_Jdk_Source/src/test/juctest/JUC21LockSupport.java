package test.juctest;

import java.security.Signature;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bangsun
 * @{link JUCTest01}
 */
@SuppressWarnings("all")
public class JUC21LockSupport {
    /**
     * 三种线程等待唤醒的方法
     * Object     wait notify  前提是获得锁才能用,必须在synchronized代码块中，而且必须先wait再notify，成对出现
     * Condition  await signal 前提是获得锁才能用,必须在lock代码块中，而且必须先await再signal，成对出现
     * LockSuppor park unpark  不需要锁，需要通行证
     */
    static Object objectLock = new Object();
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            System.out.println("LockSupport的come in");
            //被阻塞了需要许可证，发放许可证才能通过
            LockSupport.park();
            System.out.println("LockSupport的被唤醒");
        }, "A");
        threadA.start();
        Thread threadB = new Thread(() -> {
            System.out.println("LockSupport的去唤醒");
            //给A线程发放许可证
            LockSupport.unpark(threadA);
        }, "B");
        threadB.start();

        synchronizedNotify();
        lockSignal(condition);
    }

    private static void lockSignal(Condition condition) {
        new Thread(() -> {
            lock.lock();
            System.out.println("lock的come in");
            try {
                condition.await();
                System.out.println("lock的被唤醒");
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }, "A").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("lock的去唤醒");
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "B").start();
    }

    public static void synchronizedNotify() {
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println("synchronized的come in");
                try {
                    objectLock.wait();
                    System.out.println("synchronized的被唤醒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println("synchronized的去唤醒");
                objectLock.notify();
            }
        }, "B").start();

    }
}
