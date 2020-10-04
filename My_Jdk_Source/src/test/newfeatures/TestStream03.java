package test.newfeatures;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 注意lamdba中的::    双冒号运算就是Java中的[方法引用],[方法引用]的格式是 类名::方法名
 * 这种式子一般是用作Lambda表达式，Lambda有所谓懒加载嘛，不要括号就是说，看情况调用方法。
 */
public class TestStream03 {
    public static void main(String[] args) {
        //转大写
        List<String> list3 = Arrays.asList("zhangSan", "liSi", "wangWu");
        System.out.println("转换之前的数据:" + list3);
        List<String> list4 = list3.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("转换之后的数据:" + list4);
        //转类型
        List<String> list31 = Arrays.asList("1", "2", "3");
        System.out.println("转换之前的数据:" + list31);
        List<Integer> list41 = list31.stream().map(Integer::valueOf).collect(Collectors.toList());
        System.out.println("转换之后的数据:" + list41);
        //平方
        List<Integer> list5 = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5 });
        List<Integer> list6 = list5.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println("平方的数据:" + list6);
    }
}
