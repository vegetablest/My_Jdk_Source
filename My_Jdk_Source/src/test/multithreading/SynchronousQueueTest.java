package test.multithreading;


import java.util.concurrent.SynchronousQueue;

/**
 *
 * 装入一个取出一个，才能装入下一个再去取
 * @author bangsun
 */
public class SynchronousQueueTest {

    public static void main(String[] args) {
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        new Thread(()-> {
            for (int i=0;i<=5;i++){
                System.out.println("装入数据==>"+i);
                try {
                    synchronousQueue.put(""+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }},"A").start();
        new Thread(()-> {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("取出数据"+synchronousQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }},"A").start();
    }


}
