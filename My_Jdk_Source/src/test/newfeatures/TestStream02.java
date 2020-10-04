package test.newfeatures;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream流还有更多的使用方法，filter只是其中的一角而已
 */
public class TestStream02 {
    public static void main(String[] args) {
        //构建流的几种方式，直接构建
        Stream stream = Stream.of("a","b","c","d");
        String[] array = new String[]{"a","b","c","d"};
        //数组转化流两种方式，list转化流的一种方式
        stream = Stream.of(array);
        stream = Arrays.stream(array);
        List<String> list = Arrays.asList(array);
        stream = list.stream();
        //#####################################//
        /**
         * 一个Stream流只可以使用一次，这段代码为了简洁而重复使用了数次，
         * 因此会抛出 stream has already been operated upon or closed 异常。
         */
        try {
            Stream<String> stream2 = Stream.of("a", "b", "c");
            // 转换成 Array
            String[] strArray1 = stream2.toArray(String[]::new);

            // 转换成 Collection
            List<String> list1 = stream2.collect(Collectors.toList());
            List<String> list2 = stream2.collect(Collectors.toCollection(ArrayList::new));
            Set<String> set1 = stream2.collect(Collectors.toSet());
            Stack stack1 = stream2.collect(Collectors.toCollection(Stack::new));
            // 转换成 String
            String str = stream.collect(Collectors.joining()).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
