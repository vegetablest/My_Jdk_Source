package test.juctest2;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/7 13:43
 * @verson
 */
public class Thread02Performance {

    public static void main(String[] args) throws InterruptedException {
        Thread02Performance thread = new Thread02Performance();
        thread.simpleMethod1();
        thread.simpleMethod2();
    }

    public void simpleMethod1() throws InterruptedException {
        long start = System.currentTimeMillis();
        Random random = new Random();
        ArrayList<Integer> ints = new ArrayList<Integer>();
        for (int i = 0; i < 100000; i++) {
            Thread thread = new Thread(()->{ints.add(random.nextInt());});
            thread.start();
            thread.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("list的长度:"+ints.size()+",线程总共执行任务耗时："+(end-start));
    }
    public void simpleMethod2() throws InterruptedException {
        long start = System.currentTimeMillis();
        Random random = new Random();
        ArrayList<Integer> ints = new ArrayList<Integer>();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100000; i++) {
            executor.execute(()->{ints.add(random.nextInt());});
        }
        executor.shutdown();
        executor.awaitTermination(100,TimeUnit.SECONDS);
        long end = System.currentTimeMillis();
        System.out.println("list的长度:"+ints.size()+",线程池总共执行任务耗时："+(end-start));

    }
}
