package test.juctest;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ArrayList线程不安全
 *
 * @author bangsun
 */
@SuppressWarnings("all")
public class JUC07List {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<String>() {{
//            add("a");
//            add("b");
//            add("c");
//        }};
//        list.forEach(System.out::println);

        /**
         * 一边写一边读程序报错了，报了ConcurrentModificationException并发修改异常
         * 1.出现异常ConcurrentModificationException并发修改异常
         * 2.导致原因，arraylist线程不安全，边写边读就出问题了
         * 3.解决方案，使用Vector或者使用Collections.synchronizedList(new ArrayList<>());或者使用CopyOnWriteArrayList
         * */
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        List<String> list1 = new Vector<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list1.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list1);
            }, String.valueOf(i)).start();
        }
        List<String> list2 = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list2.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list2);
            }, String.valueOf(i)).start();
        }
        List<String> list3 = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list3.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list3);
            }, String.valueOf(i)).start();
        }
    }
}
