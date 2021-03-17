package test.multithreading2;

import java.util.concurrent.*;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/16 14:28
 * @verson
 */
public class FutureExample02 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("前往消费。。。");
                TimeUnit.SECONDS.sleep(5);
                return 1;
            }
        };

        FutureTask<Integer> future = new FutureTask(callable);
        executorService.submit(future);

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
        Integer integer = null;
        try {
            integer = future.get(4000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
           future.cancel(true);
        }finally {
            executorService.shutdown();
        }
        System.out.println(integer);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
