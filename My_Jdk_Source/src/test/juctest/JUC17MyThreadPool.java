package test.juctest;

import java.util.concurrent.*;

/**
 * @author bangsun
 */
@SuppressWarnings("all")
public class JUC17MyThreadPool {
    public static void main(String[] args) {
        int maximumPoolSize = Runtime.getRuntime().availableProcessors();
        System.out.println("设置最大核心数:"+maximumPoolSize);
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                maximumPoolSize+1,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    public static ExecutorService initPool() {
        return null;
    }
}
