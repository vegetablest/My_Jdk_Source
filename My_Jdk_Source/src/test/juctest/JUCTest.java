package test.juctest;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JUC Java.util.concurrent 并发编程中使用的工具类 三部分组成，还有下面两个
 * Java.util.concurrent.atomic
 * Java.util.concurrent.lock
 * 什么是进程什么是线程？
 * 进程是后台运行的程序，与操作系统有直接关系，线程是CPU操作的最小单位，而协程算是进程中的进程
 * 协程就像程序里边的线程，协程会比进程快，因为协程在用户态，进程在系统态，进程要与内核打交道比较消耗时间。
 *
 * 什么是并发什么是并行？
 * 并发是同时进行很多线程，但是CPU只能执行一个，不断切换，并行是两个程序同时运行。
 *
 * Java实现多线程的方法
 * 1.继承 Thread
 * 2.实现runncable接口
 * 3.实现callable接口
 * 4.利用线程池
 *
 * 多线程执行状态：Thread源码里边State
 * 1.new新建 2.runnable就绪 3.blocked阻塞 4.WAITING一直等 5.TIMED_WAITING等待，超时报错 6.TERMINATED
 *
 * wait和sleep
 * wait等待状态会将手里的线程让出去，sleep不会将手里的线程执行权让出去
 * */
public class JUCTest {

    public static void main(String[] args) {
        //资源类
        Tricket tricket = new Tricket();
        //线程操作资源类,线程调动不是看书写顺序，而是看系统随机调度的，不是谁先start就先执行谁
        //1.8新特性lambda表达式
        new Thread(() -> { for (int i = 0; i<=300; i++) tricket.sale();},"D").start();
        new Thread(() -> { for (int i = 0; i<=300; i++) tricket.sale();},"E").start();
        new Thread(() -> { for (int i = 0; i<=300; i++) tricket.sale();},"F").start();
        //多态匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0; i<=300;i++){
                    tricket.sale();
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0; i<=300;i++){
                    tricket.sale();
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0; i<=300;i++){
                    tricket.sale();
                }
            }
        }, "C").start();
    }
}

class Tricket {
    private int num = 300;
    /**
     *加锁，相比于synchronized,他需要解锁unlock,如果没有加上这个锁或者方法上不加synchronized
     * 如果方法上加synchronized，那么直接锁定了整个方法，锁不够细。
     * synchronized锁代码块，
     *那么会发生啥？
     * */
    Lock lock = new ReentrantLock();
    public void sale() {
        lock.lock();
        try {
            if (num>0){
                System.out.println(Thread.currentThread().getName()+"卖出"+"第"+num+"张票");
                num--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}