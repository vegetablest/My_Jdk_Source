package test.multithreading;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 基于数组的队列,要指明长度，装满容量大小就不装了，取出来一个之后再装
 * @author bangsun
 */
public class ArrayBlockingQueueTest {

    public static void main(String[] args) {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(4);
        new Thread(()-> {
            for (int i=0;i<=5;i++){
                System.out.println("装入数据==>"+i);
                try {
                    arrayBlockingQueue.put(""+i);
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
                    System.out.println("取出数据"+arrayBlockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }},"A").start();
    }
}
