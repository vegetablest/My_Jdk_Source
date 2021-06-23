package test.multithreading2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/22 16:10
 * @verson
 */
public class FutureExample04 {

    private static ThreadPoolExecutor threadPool =
            new ThreadPoolExecutor(3, 3,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>());

    private static ExecutorService executorService =  Executors.newCachedThreadPool();

    //    static FutureTaskTest futureTaskTest = new FutureTaskTest();
    public static void main(String[] args) {
        ScheduledExecutorService saveDpmScheduledThreadPool = Executors.newScheduledThreadPool(1);
        saveDpmScheduledThreadPool.scheduleWithFixedDelay(() -> {
            List<Callable<Integer>> tasks = new ArrayList<>(8);
            long l = System.currentTimeMillis();
            tasks.add(new FutureTaskTest(2));
            tasks.add(new FutureTaskTest(4));
            tasks.add(new FutureTaskTest(2));
            try {
                System.out.println(threadPool.getPoolSize());
                List<Future<Integer>> futures = threadPool.invokeAll(tasks);

                System.out.println(threadPool.getPoolSize());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("time:" + (System.currentTimeMillis() - l));
        }, 1, 1, TimeUnit.SECONDS);
    }
}


class FutureTaskTest implements Callable<Integer> {

    private int timeout;

    public FutureTaskTest(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "线程任务开始。。。睡一会");
        TimeUnit.SECONDS.sleep(timeout);
        System.out.println("任务完成");
        return 1;
    }
}