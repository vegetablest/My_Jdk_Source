package test.multithreading;

import java.util.concurrent.*;

/**
 * 线程池的体系结构，Executor--->ExecutorService--->.....ThreadPoolExecutor等
 *
 *
 * public ThreadPoolExecutor(
 *      int corePoolSize,     核心线程数，第一步，线程池放两个线程
        int maximumPoolSize,  最大线程数 第三步，第二步满了再创建3个在线程池中
        long keepAliveTime,   保持存活的时间
        TimeUnit unit,        时间单位
        BlockingQueue<Runnable> workQueue,   任务队列，第二步，核心线程满了放队列，
        RejectedExecutionHandler handler     饱和策略,默认抛出异常丢弃任务
        ){
        this(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,
        Executors.defaultThreadFactory(),handler);

        ThreadPoolExecutor.AbortPolicy()  抛出异常
        ThreadPoolExecutor.CallerRunsPolicy() 不抛异常，找人处理
        ThreadPoolExecutor.DiscardPolicy()  丢弃任务后加进来的任务
        ThreadPoolExecutor.DiscardOldestPolicy()  丢弃之前的任务

        注：main线程和worker线程是主方法不会结束，只有线程池在守护线程中执行才会结束
 * @author bangsun
 */
public class ExecutorStudy {

    Executor executor;
    ExecutorService executorService;

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(20),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 1; i <= 20; i++) {
            try {
                executorService.execute(new Thread(()-> {
                    System.out.println("线程创建");
                },"A"+i));
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("丢弃任务"+i);
            }
        }
        System.out.println("fa");
        //线程池的Executor接口的子类ExecutorService接口一般常用ThreadPoolExecutor
    }
}
