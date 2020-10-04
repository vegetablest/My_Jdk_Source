package test.juctest;

import java.util.ArrayList;

public class JUCTest02 {
    public static void main(String[] args) {
        Object object = new Object();
        ArrayList<String> list = new ArrayList();
        Thread thread01 = new Thread(() ->{
            //添加线程锁，
            synchronized (object){
                for (int i = 0; i <5 ; i++) {
                    list.add("线程产物"+i);
                    System.out.println("当前第"+i+"步");
                    if (i == 4){
                        //唤醒线程
                        object.notifyAll();
                        //关于线程的wait和sleep都要try catch
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
        Thread thread02 = new Thread(() ->{
            synchronized (object){
                while (true){
                    synchronized (object){
                        if (list.size()!=4){
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("线程一继续生产");
                        /*
                         * 为什么 Lambda 表达式(匿名类) 不能访问非 final 的局部变量呢？
                         * 因为实例变量存在堆中，而局部变量是在栈上分配，Lambda 表达(匿名类) 会在另一个线程中执行。
                         * 如果在线程中要直接访问一个局部变量，可能线程执行时该局部变量已经被销毁了，
                         * 而 final 类型的局部变量在 Lambda 表达式(匿名类) 中其实是局部变量的一个拷贝。
                         * */
                        //list = new ArrayList<>();
                        break;
                    }
                }
            }
        });

        thread02.start();
//        Thread.sleep(1000);
        thread01.start();
    }
}
