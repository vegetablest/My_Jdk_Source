package test.designpatterns;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class Singon {
    //测试三种单例模式，发现高并发下getInstance会出问题，出现多个bank实例对象
    public static void main(String[] args) {
        Long currentDate = System.currentTimeMillis();
        //由于方法上有synchronized关键字，所以依旧都是同一对象
//        for (int i = 0; i <100 ; i++) {
//            new Thread(()->{
//                Bank bank = null;
//                try {
//                    bank = Bank.getInstance();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(bank.hashCode());
//            }).start();
//        }
        //全部是一个对象，饥汉式
//        for (int i = 0; i <100 ; i++) {
//            new Thread(()->{
//                Bank bank = Bank.bank;
//                System.out.println(bank.hashCode());
//            }).start();
//        }
        //也全部是一个对象，双重检查机制
//        for (int i = 0; i <100 ; i++) {
//            new Thread(()->{
//                Bank bank = null;
//                try {
//                    bank = Bank.getInstance1();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(bank.hashCode());
//            }).start();
//       }
        //测试加synchronized代码块方式,出现了不是相同对象
//        for (int i = 0; i <100 ; i++) {
//            new Thread(()->{
//                Bank bank = null;
//                try {
//                    bank = Bank.getInstance2();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(bank.hashCode());
//            }).start();
//        }
        Long currentDate1 = System.currentTimeMillis();
        System.out.println(currentDate1-currentDate);
    }
}
//饱汉式单例
class Bank{


    //单例模式私有构造方法
    private Bank(){

    }
    //加上静态，包装类加载是只初始化一次
    private static Bank instance= null;
    //加上synchronized避免高并发情况下线程安全问题，创建两个
    //加上static保证能给静态变量赋值
    //效率差
    public static synchronized  Bank getInstance() throws InterruptedException {
        if (instance==null){
            Thread.sleep(10);
            instance = new Bank();
        }
        return instance;
    }
    //效率稍高饱汉式,双重检验DCL（double check lock）
    private static Bank instance1= null;
    public static  Bank getInstance1() throws InterruptedException {
        if (instance1==null) {
            synchronized (Bank.class) {
                if (instance1 == null) {
                    Thread.sleep(1);
                    instance1 = new Bank();
                }
            }
        }
        return instance1;
    }
    private static Bank instance2= null;
    //加上synchronized避免高并发情况下线程安全问题，创建两个
    //加上static保证能给静态变量赋值
    //效率差
    private static Object o = new Object();
    public static Bank getInstance2() throws InterruptedException {
        if (instance2==null){
            Thread.sleep(1);
            synchronized (o){
                instance2 = new Bank();
            }
        }
        return instance2;
    }
    //饥汉式，直接创建
    public static Bank bank = new Bank();
    private static Bank getBnak(){
        return bank;
    }
}
