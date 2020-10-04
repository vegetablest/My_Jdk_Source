package test.basetest;


import java.util.ArrayList;
import java.util.LinkedList;

//不使用JMH不行，测试不准，JDK9才自带JMH，JDK8需要引入依赖
public class ArrayListAndLinkListTest {
    public static int maxSize = 1000; //测试循环的次数
    public static int operSize = 100; //测试操作的次数
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0;i<maxSize;i++){
            arrayList.add(i);
            linkedList.add(i);
        }
        Long currentTimeMillis01 = System.currentTimeMillis();
        for (int i=0;i<operSize;i++){
            arrayList.add(i,i);
        }
        Long currentTimeMillis02 = System.currentTimeMillis();
        Long currentTimeMillis03 = System.currentTimeMillis();
        for (int i = 0; i <operSize ; i++) {
            linkedList.add(i,i);
        }
        Long currentTimeMillis04 = System.currentTimeMillis();
        System.out.println(currentTimeMillis01);
        System.out.println("ArrayList在头部插入100个数据耗时："+(currentTimeMillis02-currentTimeMillis01));
        System.out.println("LinkedList在头部插入100个数据耗时："+(currentTimeMillis04-currentTimeMillis03));
    }
}
