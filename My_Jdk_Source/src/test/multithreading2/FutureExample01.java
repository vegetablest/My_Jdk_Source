package test.multithreading2;

import com.sun.tools.jdi.InternalEventHandler;
import org.omg.Messaging.SyncScopeHelper;

import java.util.concurrent.*;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/15 17:30
 * @verson
 */
@SuppressWarnings("all")
public class FutureExample01 {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        Future<?> future1 = threadPool.submit(new RunableTask());
        Future<Integer> future2 = threadPool.submit(new CallTask());

        threadPool.execute(()->{
            System.out.println("这种方式不会将runable封装成futureTask");
        });
        threadPool.execute(new FutureTask<Integer>(new CallTask<Integer>()));
    }

    public static class RunableTask implements Runnable{
        @Override
        public void run() {

        }
    }
    public static class CallTask<T> implements Callable{
        @Override
        public Object call() throws Exception {
            return null;
        }
    }
}
