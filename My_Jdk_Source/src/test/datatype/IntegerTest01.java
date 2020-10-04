package test.datatype;

import org.omg.CORBA.INTERNAL;

/**
 * 依旧是从几个问题出发去探索源码
 * 1.范围            和int范围一样
 * 2.常量优化机制     主要是IntegerChace
 * 3.装箱与拆箱       1.5新特性，作为基本数据类型和包装数据类型的转化
 * */
public class IntegerTest01 {
    public static void main(String[] args) {
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        //false,new两次,两个对象在堆中
        System.out.println(a==b);
        Integer x = Integer.valueOf(1);
        //false,false
        System.out.println(x==a);
        System.out.println(x==b);
        //推荐这种写法，自动拆箱装箱底层就是这样
        Integer c = Integer.valueOf(2);
        Integer d = Integer.valueOf(2);
        //true两个是相同对象
        System.out.println(c==d);
        Integer e = 2;
        Integer f = 2;
        //true,true
        System.out.println(e == f);
        System.out.println(e == c);

        Integer h = 128;
        Integer i = 128;
        //false
        System.out.println(h==i);
        //string类型依旧能够转换
        Integer g = Integer.valueOf("2");
        Integer k = Integer.valueOf("2");
        //true,true
        System.out.println(g==k);
        System.out.println(g==c);
        Integer m = Integer.valueOf('b');
        //输出98,false
        System.out.println(m);
        System.out.println(m == c);
        //Exception in thread "main" java.lang.NumberFormatException: For input string: "1.1"
        //Integer l = Integer.valueOf("1.1");

        //底层拆箱
        int y = c.intValue();
    }
}
