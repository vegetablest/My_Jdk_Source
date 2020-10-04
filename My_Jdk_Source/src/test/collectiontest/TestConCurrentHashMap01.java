package test.collectiontest;

import java.util.concurrent.ConcurrentHashMap;
/**
 * concurrenthashmap是jdk中hashmap的一个提升，
 * CocurrentHashMap对比HashMap在HashEnty前面加了Segment段，
 * 因为HashMap不是线程安全的，并且在多线程同时写入的情况下会导致死循环，
 * 所以先多线程的环境下，一般不实用HashMap，而使用CocurrentHashMap，
 * CocurrentHashMap通过分段锁的机制，实现了多线程写入时的线程安全，也提高了多线程情况下的访问效率。
 * 这些在源码中都有所体现
 * */
public class TestConCurrentHashMap01 {
    public static void main(String[] args) {
        ConcurrentHashMap<String,Integer> concurrentHashMap = new ConcurrentHashMap();

    }
}
