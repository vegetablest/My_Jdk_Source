package test.generic;

import test.pojo.Person;

import java.util.ArrayList;

/**
 * 泛型 {@link TestGeneric02,TestGeneric03}
 *     {@link TestGeneric04,TestGeneric05,TestGeneric06}
 *     {@link TestGeneric07,TestGeneric08,TestGeneric09}
 *     {@link TestGeneric10,TestGeneric11}
 *     {@link TestGeneric12}
 * Java泛型(generies0l机制,该机制允许我们在编译时检测供了编译时类型安全监测机制，
 * 该机制允许我们在编译时检测到非法的类型数据结构。
 * 泛型的本质就是参数化类型，也就是所操作的数据类型被指定为一个参数。
 *
 * 泛型方法能使方法独立于类而产生变化
 * 如果static方法要使用泛型能力，就必须使其成为泛型方法
 * {@link TestGeneric10}
 *
 * 泛型是Java 1.5版本才引进的概念，在这之前是没有泛型的，但
 * 是，泛型代码能够很好地和之前版本的代码兼容。那是因为，泛型信息只存在于代码编译阶段，在进入.JVM之前，
 * 与泛型相关的信息会被擦除掉，我们称之为--类型擦除。
 * @author bangsun
 */
public class TestGeneric01 {

    public static void main(String[] args) {
        /**
         * 无泛型的ArrayList,什么都能加且不报错，默认是Object类型的数据集合
         * 导致获取每个Object的数据进行强转的时候会发生类型转换异常,
         * 发生在运行期不是编译器很有可能出大问题
         * */
        ArrayList arrayList = new ArrayList(){{
            add("string");
            add(12);
            add(new Person("zhangsna",23));
        }};
        /**
         * 返回Object类型
         * */
        Object o = arrayList.get(1);
        arrayList.forEach(a -> System.out.println(a));

        /**
         * 采用泛型，让报错直接发生在编译期
         * */
        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.add("ggg");
        arrayList1.add("haha");
        /*其它类型会报错的*/
//        arrayList1.add(new Person());
        /*会直接返回string类型*/
        String s = arrayList1.get(1);


    }
}
