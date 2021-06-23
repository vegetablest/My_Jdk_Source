package test.multithreading2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * <br>
 * 主要提供线程的私有变量，该变量对线程可见
 *
 * @author bangsun
 * @data 2021/6/23 10:01
 * @verson 1.0
 */
public class ThreadLocalTest {
    public static ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());
    static final int XH_COUNT = 100;

    /**
     * 讲道理每次时间都在变化，不应该出现相同时间，但是却出现了
     * 因为所有线程都用一个sdf导致线程不安全了
     * 但是如果使用ThreadLocal那就没有那么多问题了
     */
    public static void main(String[] args) {

        for (int i = 0; i < XH_COUNT; i++) {
            int time = i;
            executorService.submit(() -> {
//                String second = TimeUtils.getSecond(time);
                String second = TimeUtils.getSecond2(time);
                System.out.println(second);
            });
        }
        executorService.shutdown();
    }
}

class TimeUtils {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static String getSecond(int count) {
        Date date = new Date(1000 * count);
        return sdf.format(date);
    }

    public static String getSecond2(int count) {
        Date date = new Date(1000 * count);
        return simpleDateFormatThreadLocal.get().format(date);
    }
}
