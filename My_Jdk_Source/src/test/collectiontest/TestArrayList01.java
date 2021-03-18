package test.collectiontest;


import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 数组长度必须指定，一旦指定不能更改，必须保存同一类型的元素，
 * 增删元素比较麻烦，灵活性比较差，api较少
 * String[] strs = new String[5];
 * 增：strs[0] = "haha";
 * 扩：String strs2 = new String[strs.length + 1];
 * for(strs) copy strs2
 *
 *
 * 集合：能够动态的保存任意多个对象，方法较多，使用方便代码简洁 add remove set  get ……
 *
 * 利用反射获取list的容量，通过几种情况测试得到list大小，发现初始化，容量长度特点，进入源码进行探究
 */
public class TestArrayList01 {
    public static  Integer getCapacity(ArrayList list) throws IllegalAccessException {
        //Java反射机制
        Integer length = null;
        //获取该类
        Field field ;
        //找到该字段
        try {
            Class c = list.getClass();
            field = c.getDeclaredField("elementData");
            field.setAccessible(true);
            Object[] o =(Object[]) field.get(list);
            length = o.length;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //设置字段属性可见，能够获取
        //反射得到它
        return length;
    }
    public static void main(String[] args) throws IllegalAccessException {
        //不给他初始化大小时他的大小
        ArrayList<String> list= new ArrayList();
        Integer integer = getCapacity(list);
        //都是0，因为还没有初始化
        System.out.println("容量："+integer);
        System.out.println("大小："+list.size());
        //#######################################//
        ArrayList<String> list1 = new ArrayList();
        list1.add("1");
        Integer integer1 = getCapacity(list1);
        //输出10 和1
        System.out.println("容量："+integer1);
        System.out.println("大小："+list1.size());
        //#######################################//
        ArrayList<String> list2 = new ArrayList<>();
        for (int i = 0; i <11 ; i++) {
            list2.add(i+"");
        }
        Integer integer2 = getCapacity(list2);
        //输出15和11，因为扩容
        System.out.println("容量："+integer2);
        System.out.println("大小："+list2.size());
        //##############################################//
        //给他初始化大小
        ArrayList<String> list3= new ArrayList(11);
        Integer integer3 = getCapacity(list3);
        //输出11和0，没有add为什么直接初始化？
        System.out.println("容量："+integer3);
        System.out.println("大小："+list3.size());
        //Exception in thread "main" java.lang.IllegalArgumentException: Illegal Capacity: -2
//        ArrayList<String> list3 = new ArrayList<>(-2);
    }
}
