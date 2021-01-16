package test.juctest;


/**
 * 线程间的通信，AB两线程交替执行，对变量0进行加减操作各10次
 *
 * @author bangsun
 */
@SuppressWarnings("all")
public class JUC02Signal {

    public static void main(String[] args) throws Exception {
        AirConditioner airConditioner = new AirConditioner();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) airConditioner.increment();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) airConditioner.decrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}

/**
 * 资源类
 */
class AirConditioner {
    private int number = 0;

    /**
     * Exception in thread "A" Exception in thread "B" java.lang.IllegalMonitorStateException
     * 记得加锁，不能多线程同时操作这一个
     * */
    public synchronized void increment() throws InterruptedException {
        /**
         * 使用while进行判断，就算是notify也不会产生虚假唤醒
         * */
        if (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"线程对number进行加操作，number变成了"+number);
        /**
         * 使用notifyAll制造虚假唤醒
         * */
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        if (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"线程对number进行减操作，number变成了"+number);
        this.notifyAll();
    }

}