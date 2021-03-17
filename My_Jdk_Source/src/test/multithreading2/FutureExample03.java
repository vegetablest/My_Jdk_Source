package test.multithreading2;

import java.util.concurrent.*;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/16 14:53
 * @verson
 */
public class FutureExample03 {
    private static final String commandstr01 = "hahah";
    private static final String commandstr02 = "hahah";
    private static final String commandstr03 = "hahah";
    private static final String commandstr04 = "hahah";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();

        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                MyThreadt333 myThreadt333 = new MyThreadt333(commandstr02);
                String s = myThreadt333.get();
                return Integer.parseInt(s);
            }
        });
        FutureTask<Integer> futureTask2 = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                MyThreadt444 myThreadt333 = new MyThreadt444(commandstr02);
                String s = myThreadt333.get();
                return Integer.parseInt(s);
            }
        });

        executorService.execute(futureTask1);
        executorService.execute(futureTask2);

        try {
            Integer integer = futureTask1.get();
            Integer integer1 = futureTask2.get();
            System.out.println(integer);
            System.out.println(integer1);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            futureTask1.cancel(true);
            futureTask2.cancel(true);
        }
        long end = System.currentTimeMillis();
        System.out.println("时间："+(end-start));
        executorService.shutdown();
    }
}
