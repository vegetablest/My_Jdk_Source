package test.juctest;

import java.util.concurrent.*;

/**
 *
 */
@SuppressWarnings("all")
public class JUC16MyThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
        try{
            for (int i = 1; i <= 10; i++) {
                threadPool1.execute(new Thread(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                },"线程"+i));
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool1.shutdown();
        }
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
        try{
            for (int i = 1; i <= 10; i++) {
                threadPool2.execute(new Thread(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                },"线程"+i));
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool2.shutdown();
        }
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
        try{
            for (int i = 1; i <= 10; i++) {
                threadPool3.execute(new Thread(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                },"线程"+i));
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool3.shutdown();
        }
    }
}
