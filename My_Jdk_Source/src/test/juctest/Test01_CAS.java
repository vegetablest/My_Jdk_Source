package test.juctest;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class Test01_CAS {
    private static volatile int m = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        CountDownLatch countDownLatch = new CountDownLatch(threads.length);
        for (int i = 0; i <threads.length ; i++) {
            threads[i]=new Thread(()->{
                for (int j = 0; j <10000 ; j++) {
                    m++;
                }
                countDownLatch.countDown();
            });
        }
        Arrays.stream(threads).forEach((t)->t.start());
        countDownLatch.await();
        //输出56923，不是10000000
        System.out.println(m);
    }
}
