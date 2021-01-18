package test.juctest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * set和list一样
 *
 * @author bangsun
 */
@SuppressWarnings("all")
public class JUC08Set {

    public static void main(String[] args) {
        /**
         * ConcurrentModificationException
         * */
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }, "A").start();
        }

        Set<String> set1 = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set1.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set1);
            }, String.valueOf(i)).start();
        }

        Set<String> set2 = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set2.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set2);
            }, String.valueOf(i)).start();
        }
    }
}
