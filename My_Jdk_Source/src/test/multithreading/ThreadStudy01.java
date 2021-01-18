package test.multithreading;

/**
 * java的线程是内核线程，能够通过计算机的线程数看到
 * @author bangsun
 */
public class ThreadStudy01 {
    public static void main(String[] args) {
        for (int i = 0; i < 300; i++) {
            new Thread(()-> {
                try {
                    while (true){
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
