package test.juctest;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class Test02_CAS {
    //private static volatile int m = 0;
    private static int m = 0;

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Thread[] threads = new Thread[100];
        CountDownLatch countDownLatch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                //加锁
                synchronized (o) {
                    for (int j = 0; j < 10000; j++) {
                        m++;
                    }
                }
                countDownLatch.countDown();
            });
        }
        Arrays.stream(threads).forEach((t) -> t.start());
        countDownLatch.await();
        //是10000000
        System.out.println(m);
    }
}
