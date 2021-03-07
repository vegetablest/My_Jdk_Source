package test.juctest2;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/7 13:25
 * @verson
 */
public class Thread01MethodStart extends Thread{

    @Override
    public void run() {
        System.out.println("Run Method Start ...");
    }

    public static void main(String[] args) {
        Thread01MethodStart test1 = new Thread01MethodStart();
        /**
         * 都会输出一样，但是run方法只是普通方法的调用
         * start则是多线程方法调用，可以看左下角dump信息，
         * 发现run方法和主方法是一个线程
         * 而start方法却是另外一个线程
         * */
        test1.run();
        test1.start();
    }
}
