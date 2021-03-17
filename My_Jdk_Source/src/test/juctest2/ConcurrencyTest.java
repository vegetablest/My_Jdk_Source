package test.juctest2;

/**
 * <br>
 * 并发与串行化对比，百万之前串行化快，因为线程的创建包括上下文切换都是需要时间和资源的
 * @author bangsun
 * @data 2021/3/17 13:06
 * @verson
 */
public class ConcurrencyTest {
//    private static final long count = 10000L;
//    private static final long count = 100000L;
    private static final long count = 10000000L;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < count; i++) {
            a+=5;
        }
        int b=0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        long end = System.currentTimeMillis();
        System.out.println("serial用时："+(end-start)+",b="+b);
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            int a = 0;
            for (int i = 0; i < count; i++) {
                a += 5;
            }
        });
        thread.start();
        int b=0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        thread.join();
        long end = System.currentTimeMillis();
        System.out.println("concurrency用时："+(end-start)+",b="+b);
    }
}
