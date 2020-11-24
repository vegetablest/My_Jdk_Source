package test.newfeatures;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 关于lamdba
 *
 * Lambda 表达式(lambda expression)是一个匿名函数，
 * Lambda表达式基于数学中的λ演算得名，直接对应于其中的lambda抽象(lambda abstraction)，
 * 是一个匿名函数，即没有函数名的函数。Lambda虽然简化了代码的编写，但同时也减少了可读性。
 * 函数式接口:就是只有一个抽象方法的接口. lambda表达式和方法引用,只能用在函数式接口上
 *
 * 一个 Lambda 表达式可以有零个或多个参数
 * 参数的类型既可以明确声明，也可以根据上下文来推断。例如：(int a)与(a)效果相同
 * 所有参数需包含在圆括号内，参数之间用逗号相隔。例如：(a, b) 或 (int a, int b) 或 (String a, int b, float c)
 * 空圆括号代表参数集为空。例如：() -> 42
 * 当只有一个参数，且其类型可推导时，圆括号（）可省略。例如：a -> return a*a
 * Lambda 表达式的主体可包含零条或多条语句
 * 如果 Lambda 表达式的主体只有一条语句，花括号{}可省略。匿名函数的返回类型与该主体表达式一致
 * 如果 Lambda 表达式的主体包含一条以上语句，则表达式必须包含在花括号{}中（形成代码块）。匿名函数的返回类型与代码块的返回类型一致，若没有返回则为空
 */
public class TestLambda01 {
    public static void main(String[] args) {
        TestLambda01 testLambda01 = new TestLambda01();
        HashMap<String,String> map = new HashMap();
        map.put("a","a");
        map.put("c","c");
        map.put("b","b");
        //传统的遍历方法
        for (String key : map.keySet()) {
            System.out.println("key="+key+","+"value="+map.get(key));
        }
        /**
         * 关于foreach
         * foreach适用于数组或实现了iterator的集合类。foreach就是使用Iterator接口来实现对集合的遍历的。
         * 在用foreach循环遍历一个集合时，不能改变集合中的元素，如增加元素、修改元素。
         * 否则会抛出ConcurrentModificationException异常。好像集合类都实现了Iterator
         */
        //lambda表达式遍历
        map.forEach((k,v) -> {
            System.out.println("key="+k+","+"value="+v);
        });
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        //lamdba表达式遍历,只有一个参数且类型可推导
        list.forEach(v -> System.out.println("value="+v));
        //lamdba表达式还能代替匿名内部类，eg:实现线程
        new Thread(()-> System.out.println("hello"),"线程A");
        //自己写接口调用实现



    }

}
