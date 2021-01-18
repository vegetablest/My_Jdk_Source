package test.juctest;

import javax.swing.plaf.synth.SynthSpinnerUI;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition精准唤醒
 * 顺序调用A->B->C
 * @author bangsun
 */
@SuppressWarnings("all")
public class JUC05ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        },"C").start();
    }
}

class ShareResource{
    /**
     * A-1,B-2.C-3
     * */
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5(){

        lock.lock();
        try {
            while (num != 1){
                condition1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("线程"+Thread.currentThread().getName()+"打印:"+num);
            }
            num = 2;
            //精确唤醒
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(){

        lock.lock();
        try {
            while (num != 2){
                condition2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("线程"+Thread.currentThread().getName()+"打印:"+num);
            }
            num = 3;
            //精确唤醒
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(){

        lock.lock();
        try {
            while (num != 3){
                condition3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println("线程"+Thread.currentThread().getName()+"打印:"+num);
            }
            num = 1;
            //精确唤醒
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
