package test.juctest;


import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 * @author bangsun
 */
@SuppressWarnings("all")
public class JUC11CountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"离开教室");
                countDownLatch.countDown();
            },"同学"+i).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"班长走了");

//        for (int i = 1; i <= 6; i++) {
//            new Thread(()->{
//                System.out.println(Thread.currentThread().getName()+"离开教室");
//            },"同学"+i).start();
//        }
//        System.out.println(Thread.currentThread().getName()+"班长走了");
    }
}
