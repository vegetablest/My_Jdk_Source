package test.juctest;

import java.util.concurrent.TimeUnit;

/**
 * 什么是锁
 */
public class JUC06Lock {
    public static void main(String[] args) {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone1.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
//                phone1.sendMes();
                phone1.sendMes();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
        new Thread(() -> {
            try {
                phone1.hello();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
    }
}

/**
 * 题目:多线程8锁
 * 1标准访问，请问先打印邮件还是短信?   先邮件再短信，因为同步锁一直在邮件手上，统一资源类同一时间只能有一个线程访问同步方法
 * 2邮件方法暂停4秒钟，请问先打印邮件还是短信?  先邮件再短信，因为邮件睡了，但是不释放锁，此时锁是this
 * 3新增一个普通方法hello()，请问先打印邮件还是hello?  先打印hello，因为hello没有synchronized
 * 4两部手机，请问先打印邮件还是短信?   先发短信，因为是两个对象两个this,互不干扰
 * 5两个静态同步方法，同一部手机，请问先打印邮件还是短信?     先打印邮件，因为静态方法锁是class对象
 * 6两个静态同步方法，2部手机，请问先打印邮件还是短信?       先打印邮件，因为静态方法锁是class对象
 * 7 1个普通同步方法,1个静态同步方法，1部手机，请问先打印邮件还是短信?  先打印短信再打印邮件，因为锁的不是同一个对象
 * 8 1个普通同步方法,1个静态同步方法，2部手机，请问先打印邮件还是短信?  先打印短信再打印邮件，因为锁的不是同一个对象
 * */
class Phone {

    public static synchronized void sendEmail() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(3000);
        System.out.println("=====>发邮件");
    }

    public synchronized void sendMes() throws InterruptedException {
        System.out.println("=====>发短信");
    }

    public void hello() throws InterruptedException {
        System.out.println("=====>hello");
    }
}
