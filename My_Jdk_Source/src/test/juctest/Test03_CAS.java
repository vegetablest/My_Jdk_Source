package test.juctest;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Test03_CAS {
    private static AtomicInteger m = new AtomicInteger(0);//自旋锁，轻量级锁
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        //countDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行。
        //是通过一个计数器来实现的，计数器的初始值是线程的数量。
        // 每当一个线程执行完毕后，计数器的值就-1，当计数器的值为0时，表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了
        CountDownLatch countDownLatch = new CountDownLatch(threads.length);
        for (int i = 0; i <threads.length ; i++) {
            threads[i]=new Thread(()->{
                for (int j = 0; j <10000 ; j++) {
                    //点进去看源码能看到每次怎么操作的，源码是compareAndSwapInt，CAS比较并交换，本地方法
                    //public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);
                    m.incrementAndGet();
                }
                //将count值减1
                countDownLatch.countDown();
            });
        }
        Arrays.stream(threads).forEach((t)->t.start());
        //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
        countDownLatch.await();
        //输出是10000000
        System.out.println(m);
    }
}
