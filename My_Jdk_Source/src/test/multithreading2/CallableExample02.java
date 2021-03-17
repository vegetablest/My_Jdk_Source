package test.multithreading2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/15 16:48
 * @verson
 */
public class CallableExample02 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("前往消费。。。");
                TimeUnit.SECONDS.sleep(5);
                return 1;
            }
        };
        FutureTask<Integer> future = new FutureTask(callable);
        new Thread(future).start();


        new Thread(()->{
            try {
                System.out.println("睡两秒");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        if (!future.isDone()) {
            System.out.println("还没结束。可以cancel方法取消");
        }
        Integer integer = future.get();
        System.out.println(integer);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
