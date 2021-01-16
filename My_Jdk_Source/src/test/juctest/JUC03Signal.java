package test.juctest;

/**
 * 多个生产者消费者，使用notifyAll，出现虚假唤醒问题
 * @author bangsun
 */
@SuppressWarnings("all")
public class JUC03Signal {
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
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) airConditioner.decrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) airConditioner.decrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "D").start();
    }
}
