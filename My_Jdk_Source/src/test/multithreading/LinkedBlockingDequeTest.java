package test.multithreading;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;

/**
 * 随便装，无所谓,一下子装入再慢慢取
 * @author bangsun
 */
public class LinkedBlockingDequeTest {
    public static void main(String[] args) {
        LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();
        new Thread(()-> {
            for (int i=0;i<=5;i++){
                System.out.println("装入数据==>"+i);
                try {
                    linkedBlockingDeque.put(""+i);
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
                    System.out.println("取出数据"+linkedBlockingDeque.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }},"A").start();
    }

}
