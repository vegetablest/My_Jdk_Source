package test.multithreading2;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/22 16:10
 * @verson
 */
public class FutureExample05 {

    private static ThreadPoolExecutor threadPool =
            new ThreadPoolExecutor(3, 3,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>());

    public static void main(String[] args) {
        ScheduledExecutorService saveDpmScheduledThreadPool = Executors.newScheduledThreadPool(1);
        saveDpmScheduledThreadPool.scheduleWithFixedDelay(() -> {
            long l = System.currentTimeMillis();
            List<CompletedFuture<Integer>> tasks = new ArrayList<>(8);

            System.out.println(threadPool.getPoolSize());
            System.out.println("time:" + (System.currentTimeMillis() - l));
        }, 1, 1, TimeUnit.SECONDS);
    }
}


class FutureTaskTest5 implements Callable<Integer> {

    private int timeout;

    public FutureTaskTest5(int timeout) {
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