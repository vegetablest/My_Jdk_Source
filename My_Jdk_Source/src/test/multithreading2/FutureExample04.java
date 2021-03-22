package test.multithreading2;

import java.util.concurrent.*;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/22 16:10
 * @verson
 */
public class FutureExample04 {

    private static ExecutorService executorService = Executors.newCachedThreadPool();
    static FutureTaskTest futureTaskTest = new FutureTaskTest();
    public static void main(String[] args) {
        ScheduledExecutorService saveDpmScheduledThreadPool = Executors.newScheduledThreadPool(1);
        saveDpmScheduledThreadPool.scheduleWithFixedDelay(()->{
            Future<Integer> submit = executorService.submit(futureTaskTest);
        }, 1, 1, TimeUnit.SECONDS);
    }
}




class FutureTaskTest implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"线程任务开始。。。睡一会");
        TimeUnit.MILLISECONDS.sleep(3500);
        return 1;
    }
}