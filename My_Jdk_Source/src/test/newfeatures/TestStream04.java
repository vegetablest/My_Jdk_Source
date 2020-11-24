package test.newfeatures;

import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1.stream 不会存储数据,所以不用担心内存爆炸
 * 2.stream 不会改变数据
 * 3.stream 不可重复使用
 * 4.stream 多节点时每个节点是一个流,每走过一步,之前的流作废
 */
public class TestStream04 {

    public static List<Apple> appleStore = new ArrayList<Apple>() {{
        add(new Apple("red", 1, 200));
        add(new Apple("greed", 2, 300));
        add(new Apple("red", 3, 230));
        add(new Apple("red", 4, 210));
        add(new Apple("yellow", 5, 222));
    }};

    public static void main(String[] args) {
        /**
         * 流的生成
         * 1.容器直接转
         * 2.数组直接转
         * 3.new
         * */
        Stream<Apple> stream = appleStore.stream();
        /*每执行一个节点都会变成新的流,即不可重复操作,中间节点是懒加载的,执行完中间节点返回新的stream,终值节点才会结束*/
        Stream<Apple> stream1 = stream.filter(a -> a.getColor().equals("red"));
        Stream<Apple> stream2 = stream1.filter(apple -> apple.getId() > 2);
        Object[] string = stream2.toArray();
        for (Object o : string) {
            System.out.println(o.toString());
        }
        /**
         * 会报错,因为流stream1已经使用
         * stream has already been operated upon or closed
         */
//        stream1.filter(apple -> apple.getColor().equals("red"));

        String[] str = new String[]{"是","否"};
        List<String> yes = Arrays.asList(str).stream().peek(a -> System.out.println(a)).filter(a -> a.equals("是")).collect(Collectors.toList());
        yes.forEach(a-> System.out.println(a));
        /**
         * 影响方式:过滤和转换
         * map:转换,往下传的不再是apple而是color
         * */
        Object[] objects = appleStore.stream()
                .filter(a -> a.getColor().equals("red")||a.getColor().equals("greed"))
                .map(a -> a.getColor())
                .distinct()
                .peek(a -> System.out.println(String.format("这里a都不能get了,因为从apple转换为color了:%s", a)))
                .toArray();
        /**
         * 采集
         * list,set,map,group by,数组,最大,
         * */
        List<Apple> collect = appleStore.stream().collect(Collectors.toList());
        Set<Apple> collect1 = appleStore.stream().collect(Collectors.toSet());
        Map<Integer, String> collect2 = appleStore.stream().collect(Collectors.toMap(a -> a.getId(), a -> a.getColor()));
//        Map<String, Apple> collect3 = appleStore.stream().collect(Collectors.toMap(a -> a.getColor(), a -> a));

        System.out.println("debug");


    }


}

class Apple {

    public String color;
    public Integer id;
    public int weight;

    public Apple() {
    }

    public Apple(String color, Integer id, int weight) {
        this.color = color;
        this.id = id;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Apple apple = (Apple) o;

        if (weight != apple.weight) {
            return false;
        }
        if (color != null ? !color.equals(apple.color) : apple.color != null) {
            return false;
        }
        return id != null ? id.equals(apple.id) : apple.id == null;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", id=" + id +
                ", weight=" + weight +
                '}';
    }
}