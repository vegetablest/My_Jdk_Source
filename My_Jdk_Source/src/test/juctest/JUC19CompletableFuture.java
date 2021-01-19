package test.juctest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author bangsun
 */
@SuppressWarnings("all")
public class JUC19CompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = new CompletableFuture();
        //异步调用没有返回值
        CompletableFuture<Void> voidCompletableFuture = completableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "当前线程没有返回,更新成功");
        });
        //null
        System.out.println(voidCompletableFuture.get());

        //异步调用有返回值
        CompletableFuture<Integer> integerCompletableFuture = completableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "当前线程有返回,更新成功");
            return 1024;
        });
        //1024
        System.out.println(integerCompletableFuture.get());
        System.out.println("hhhh");

        CompletableFuture<Integer> integerCompletableFuture1 = completableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "异步异常测试");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int age = 10 / 0;
            return 1024;
        });

        Integer integer = integerCompletableFuture1.whenComplete((t, u) -> {
            System.out.println("ttttt:" + t);
            System.out.println("uuuuu:" + u);
        }).exceptionally(f -> {
            System.out.println("exception:" + f.getMessage());
            return 404;
        }).get();
        System.out.println(integer);
        System.out.println("wwwww");
    }
}
