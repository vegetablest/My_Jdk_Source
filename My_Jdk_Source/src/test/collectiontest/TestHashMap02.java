package test.collectiontest;

import java.util.HashMap;

/**
 * debug测试resize(),resize()方法打断点,初始化打一个断点，扩容打一个
 * 扩容初始化都是一个方法
 * debug不进去，因为是自己的jdk
 * */
public class TestHashMap02 {

    public static void main(String[] args) {
        HashMap<String,Integer> hashMap = new HashMap<>();
        //第一次put初始化
        hashMap.put("1",1);
        //
        for (int i = 2; i <13 ; i++) {
            hashMap.put(i+"",i);
        }
        //第13次put扩容
        hashMap.put("13",13);
    }
}
