package test.generic;

/**
 * 泛型通配符
 * 类型通配符一般是使用"?"代替具体的类型实参。
 * 所以，类型通配符是类型实参，而不是类型形参。
 *
 * @author bangsun
 */
public class TestGeneric11<E> {

    public static void main(String[] args) {
        /**
         * 通配符
         * */
        Box<Number> box1 = new Box<>();
        box1.setFirst(100);
        showBox(box1);

        /**
         * ？ 类型通配符
         * */
        Box<Integer> box2 = new Box<>();
        box2.setFirst(100);
        /*D:\bangsun\gitspace\My_Jdk_Source\src\test\generic\TestGeneric11.java:18:17
            java: 不兼容的类型: test.generic.Box<java.lang.Integer>无法转换为test.generic.Box<java.lang.Number>*/
//        showBox(box2);
        showBox2(box1);
        showBox2(box2);

        /**
         * 类型通配符上限
         * */
        showBox3(box1);
        showBox3(box2);
        //showBox3(new Box<Object>());

    }

    public static void showBox(Box<Number> box) {
        Number first = box.getFirst();
        System.out.println(first);
    }

    /**
     * 泛型通配符 ？代表类型实参，代表任意类型
     */
    public static void showBox2(Box<?> box) {
        Object first = box.getFirst();
        System.out.println(first);
    }

    /**
     * 泛型通配符 ？代表类型实参，代表任意类型  限定上限Number
     * 放Number及其子类，最高是Number
     */
    public static void showBox3(Box<? extends Number> box) {
        /*内部不可以填充元素*/
        Object first = box.getFirst();
        System.out.println(first);
    }
    /**
     * 泛型通配符 ？代表类型实参，代表任意类型  限定下限Number
     * 放Integer及其父类，最低是Integer
     * 遍历限定下限的类型通配符，全是拿Object接收到
     */
    public static void showBox4(Box<? super Integer> box) {
        /*内部可以填充元素，但是不保证元素类型*/
        Object first = box.getFirst();
        System.out.println(first);
    }

}

class Box<E> {
    private E first;

    public E getFirst() {
        return first;
    }

    public void setFirst(E first) {
        this.first = first;
    }
}