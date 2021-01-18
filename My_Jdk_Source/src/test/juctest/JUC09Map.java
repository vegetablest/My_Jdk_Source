package test.juctest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map
 * @author bangsun
 */
public class JUC09Map {
    public static void main(String[] args) {
        /**
         * ConcurrentModificationException
         * */
//        Map<String,String> map = new HashMap();
//        for (int i = 0; i < 30; i++) {
//            new Thread(() -> {
//                map.put(UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,8));
//                System.out.println(map);
//            }, "A").start();
//        }
        Map<String,String> map1 = Collections.synchronizedMap(new HashMap());
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map1.put(UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map1);
            }, "A").start();
        }
        Map<String,String> map2 = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map2.put(UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map2);
            }, "A").start();
        }

    }
}
