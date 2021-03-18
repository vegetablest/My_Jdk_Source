package test.collectiontest;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <br>
 * 顺序存储，每个元素都有一个索引
 * @author bangsun
 * @data 2021/3/18 10:29
 * @verson
 */
public class TestArrayList02 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>() {{
            add("fs");
            add("we");
            add("gs");
            add("aw");
        }};
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()){
            /**先next再remove不会报错，因为会调整指针位置，先remove再next不行，*/
            String next = iterator.next();
//            iterator.remove();
            System.out.print(next+"\t");
        }
        System.out.println();

        for (String s : list) {
            System.out.print(s+"\t");
        }
        System.out.println();

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i)+"\t");
        }
        System.out.println();

        list.forEach(o-> System.out.print(o+"\t"));
        System.out.println();

    }
}
