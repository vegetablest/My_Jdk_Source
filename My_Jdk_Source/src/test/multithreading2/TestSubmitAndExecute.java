package test.multithreading2;

import java.util.concurrent.*;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/22 9:14
 * @verson
 */
public class TestSubmitAndExecute {
    static ExecutorService executor = Executors.newCachedThreadPool();
    public static void main(String[] args) {
        /**runnable,有返回值，但是返回值为null*/
        Object object = testSubmitRunnable(new RunnableTask());
        System.out.println("runnable的返回值"+object);
        /**runnable,无返回值*/
        testExecuteRunnable(new RunnableTask());
        initExecutors();
        waitToTerminated();
        System.out.println("=================================================");
        /**runnable,指定返回值*/
        Integer i = testSubmitRunnable(new RunnableTask(), 3);
        System.out.println(i);
        Boolean bool = testSubmitRunnable(new RunnableTask(), true);
        System.out.println(bool);
        String str = testSubmitRunnable(new RunnableTask(), "你好吗");
        System.out.println(str);
        initExecutors();
        waitToTerminated();
        System.out.println("=============================================");
        /**实现callable方法，返回值不用说，是由callable决定的*/
        Object o = testSubmitCallable(new CallableTask());
        System.out.println(o);
        initExecutors();
        waitToTerminated();
        System.out.println("=============================================");
        /**runnable遭遇异常*/
//        testExecuteRunnable(new ExceptionRunableTask());
        initExecutors();
        waitToTerminated();
        /**callable遭遇异常，get的时候才有*/
//        testSubmitCallable(new ExceptionCallableTask());
        initExecutors();
    }

    private static void waitToTerminated() {
        if (executor.isTerminated()) {
            executor = Executors.newCachedThreadPool();
        }
    }

    private static void initExecutors() {
        executor.shutdown();
        /**自旋，直到所有任务都完成*/
        while (!executor.isTerminated()) {
        }
    }

    /**
     * 测试 submit(Callable<T> task)
     * @param callable
     * @param <T>
     * @return
     */
    public static <T> T testSubmitCallable(Callable callable) {
        Future<T> future = executor.submit(callable);
        T result = null;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 测试submit(Runnable task, T result)
     *
     * @param runnable
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T testSubmitRunnable(Runnable runnable, T t) {
        Future<T> future = executor.submit(runnable, t);
        T result = null;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 测试 submit(Runnable task)
     * submit提交Runnable任务会默认返回null
     *
     * @param runnable
     * @return
     */
    public static Object testSubmitRunnable(Runnable runnable) {
        Future<?> future = executor.submit(runnable);
        Object v = null;
        try {
            v = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return v;
    }

    /**
     * 测试 execute(Runnable command)
     * execute会直接抛出异常，submit只有通过调用Future对象的get方法才能获取异常
     *
     * @param runnable
     */
    public static void testExecuteRunnable(Runnable runnable) {
        executor.execute(runnable);
    }
}

class CallableTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 520; i++) {
            sum += i;
        }
        return sum;
    }
}

/**
 * 会抛异常的CallableTask
 */
class ExceptionCallableTask implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        int num = 1 / 0;
        return false;
    }
}

class RunnableTask implements Runnable {
    @Override
    public void run() {
        System.out.println("I am a runnable task");
    }
}

/**
 * 会抛异常的RunnableTask
 */
class ExceptionRunableTask implements Runnable {
    @Override
    public void run() {
        int num = 1 / 0;
    }
}