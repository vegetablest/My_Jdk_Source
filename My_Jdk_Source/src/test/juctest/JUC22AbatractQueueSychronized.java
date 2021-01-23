package test.juctest;

import java.time.LocalDate;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bangsun
 */
public class JUC22AbatractQueueSychronized {
    static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        /**从这先加锁，进去分析AQS*/
        lock.lock();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /**解锁之后其它线程就能抢到锁了，进去分析AQS*/
            lock.unlock();
        }
    }
}
