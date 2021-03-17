package test.multithreading2;

import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.*;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/15 16:10
 * @verson
 */
public class CallableExample01 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            System.out.println("睡五秒");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread1.join();

       Thread thread2 =  new Thread(()->{
            try {
                System.out.println("睡两秒");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();
        thread2.join();

        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }


}
