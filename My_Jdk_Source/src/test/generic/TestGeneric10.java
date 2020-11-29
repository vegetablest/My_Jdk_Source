package test.generic;

import test.pojo.Person;

import java.util.ArrayList;

/**
 * 泛型方法，声明了泛型列表的方法才是泛型方法，之前的都是泛型类
 * 还是常用<K,V,T,E>作为泛型
 * @author bangsun
 */
public class TestGeneric10 {


    public static void main(String[] args) {

        /**
         * 与泛型类调用的成员变量的泛型方法不同，泛型类需要给成员变量指定泛型然后去调用，必须遵循类的泛型
         *
         * 泛型方法是调用方法时指定泛型，都是遵循方法的，而且可以定义静态方法
         * */
        TestGeneric10 testGeneric10 = new TestGeneric10();
        String key = testGeneric10.getKey(new ArrayList<String>() {{
            add("haha");
            add("hehe");
            add("xixi");
        }});
        System.out.println("key = " + key);
        Integer key1 = testGeneric10.getKey(new ArrayList<Integer>() {{
            add(123);
            add(1234);
            add(14243);
        }});
        System.out.println("key1 = " + key1);

        TestGeneric10.printType("haha",100,new Person());
        TestGeneric10.print(1,2,3,4,5);
        TestGeneric10.print("1","2","3","4","5");
    }
    /**
     * 泛型方法
     * */
    public <E> E getKey(ArrayList<E> arrayList){
        return arrayList.get(1);
    }
    /**
     * 静态泛型方法
     * */
    public static <T,E,K> void printType(T t,E e,K k){
        System.out.println(t+"\t"+t.getClass().getSimpleName());
        System.out.println(e+"\t"+e.getClass().getSimpleName());
        System.out.println(k+"\t"+k.getClass().getSimpleName());
    }
    /**
     * 泛型可变参数方法
     * */
    public static <E> void print(E... e){
        for (E e1 : e) {
            System.out.println(e1+":"+e.getClass().getSimpleName());
        }
    }
}
