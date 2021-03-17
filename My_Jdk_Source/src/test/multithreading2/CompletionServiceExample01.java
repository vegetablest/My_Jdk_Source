package test.multithreading2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/16 14:36
 * @verson
 */
@SuppressWarnings("all")
public class CompletionServiceExample01 {

    private static final String commandstr01 = "hahah";
    private static final String commandstr02 = "hahah";
    private static final String commandstr03 = "hahah";
    private static final String commandstr04 = "hahah";

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        CompletableFuture.supplyAsync(new MyThreadt444(commandstr02),executorService).whenComplete((result, e) -> {
            //执行线程执行完以后的操作。
            System.out.println(result + " " + e);
        }).exceptionally((e) -> {
            //抛出异常
            System.out.println("exception " + e);
            return "exception";
        });

        CompletableFuture.supplyAsync(new MyThreadt333(commandstr02),executorService).whenComplete((result, e) -> {
            //执行线程执行完以后的操作。
            System.out.println(result + " " + e);
        }).exceptionally((e) -> {
            System.out.println("exception " + e);
            return "exception";
        });

        executorService.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("时间："+(end-start));
    }
}


class MyThreadt333 implements Supplier<String> {
    // 要运行的mingling
    private String commandstr;
    public MyThreadt333(String commandstr) {
        this.commandstr = commandstr;
    }
    @Override
    public String get() {
        int sum = 0;
        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sum += i;
            System.out.println("Mythread333: "+i);
        }
        return String.valueOf(sum+300000);
    }
}

class MyThreadt444 implements Supplier<String>{
    // 要运行的mingling
    private String commandstr;
    public MyThreadt444(String commandstr) {
        this.commandstr = commandstr;
    }
    @Override
    public String get() {
        int sum = 0;
        for (int i = 0; i < 40; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sum += i;
            System.out.println("Mythread444: "+i);
        }
        return String.valueOf(sum+400000);
    }
}