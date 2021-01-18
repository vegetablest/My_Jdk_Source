package test.juctest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Java获取多线程的新接口，callable接口
 * @author bangsun
 */
@SuppressWarnings("all")
public class JUC10Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        MyRun myRun = new MyRun();
//        Thread thread = new Thread(myRun,"A");
//        thread.start();

        MyCall myCall = new MyCall();
        //FutureTask需要一个Callable
        FutureTask futureTask = new FutureTask(myCall);
        //new Thread需要一个实现Runable接口的实现类，而FutureTask是RunnableFuture的实现了
        //RunnableFuture是Runnable的子接口，通过FutureTask的get方法获取返回值
         new Thread(futureTask,"A").start();
         new Thread(futureTask,"B").start();
        System.out.println(Thread.currentThread().getName()+"计算完成");
        //获取结果,一般放到最后边
        System.out.println(futureTask.get());
    }
}

class MyCall implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        /**
         * 就算睡4秒，会先执行其他的，最后等他算完再返回结果
         * */
        System.out.println("callable====>come in here");
        TimeUnit.SECONDS.sleep(4);
        return 1024;
    }
}
class MyRun implements Runnable{
    @Override
    public void run() {
        System.out.println("runnable====>come in here");
    }
}