package test.juctest2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/7 14:34
 * @verson
 */
public class Thread03ComparePool {

    public static void main(String[] args) {
        /**cache线程池，他会新建线程，线程最大是Integer.MAX,但是如果有空闲线程他会利用空闲线程，又叫线程复用技术*/
        ExecutorService cache = Executors.newCachedThreadPool();
        /**定长线程池，指定多少线程就是多少线程*/
        ExecutorService fixed = Executors.newFixedThreadPool(10);
        /**一个线程，核心线程数为1*/
        ExecutorService single = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 100; i++) {
            cache.execute(new MyTask(i));
//            fixed.execute(new MyTask(i));
//            single.execute(new MyTask(i));
        }
        cache.shutdown();
//        fixed.shutdown();
//        single.shutdown();
    }
}
class MyTask implements Runnable{

    private int i = 0;

    public MyTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"============"+i);
        try {
            /**假装复杂逻辑*/
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}