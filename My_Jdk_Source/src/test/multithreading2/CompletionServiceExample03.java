package test.multithreading2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/17 14:39
 * @verson
 */
public class CompletionServiceExample03 {
    private static ExecutorService executor = Executors.newCachedThreadPool();

    private static List<CompletableFuture<Integer>> futures = new ArrayList<>();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        futures.add(CompletableFuture.supplyAsync(()->{
            try {
                System.out.println("线程："+Thread.currentThread().getName()+",在这里睡3秒，就当是业务逻辑");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3;
        },executor));
        futures.add(CompletableFuture.supplyAsync(()->{
            try {
                System.out.println("线程："+Thread.currentThread().getName()+",在这里睡6秒，就当是业务逻辑");
                TimeUnit.SECONDS.sleep(6);
                int b= 10/0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 6;
        },executor));
        futures.add(CompletableFuture.supplyAsync(()->{
            try {
                System.out.println("线程："+Thread.currentThread().getName()+",在这里睡4秒，就当是业务逻辑");
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 4;
        },executor));
        ArrayList<Integer> list = new ArrayList<>();
        futures.forEach(f ->{
            try {
                Integer integer = f.get(7, TimeUnit.SECONDS);
                System.out.println(integer);
                list.add(integer);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(list);
        System.out.println("走完了，时间:"+(end-start));
        executor.shutdown();
    }
}
