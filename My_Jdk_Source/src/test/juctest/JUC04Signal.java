package test.juctest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JUC解决虚假唤醒
 * @author bangsun
 */
@SuppressWarnings("all")
public class JUC04Signal {
    public static void main(String[] args) throws Exception {
        AirConditioner2 airConditioner = new AirConditioner2();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) airConditioner.increment();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) airConditioner.decrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) airConditioner.increment();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) airConditioner.decrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "D").start();
    }
}
/**
 * 资源类
 */
class AirConditioner2 {
    private int number = 0;

    /**
     * 新版 lock  condition
     * */
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"线程对number进行加操作，number变成了"+number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"线程对number进行减操作，number变成了"+number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }


}