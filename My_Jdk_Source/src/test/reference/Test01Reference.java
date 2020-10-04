package test.reference;

import test.pojo.Animal;

import java.io.IOException;

/**
 * java有四种引用 强、软、弱、虚
 * pojo里边animal重写finalize方法，测试有没有被回收
 * */
public class Test01Reference {
    /*
    * 强引用，如果一个对象具有强引用，那就类似于必不可少的生活用品，垃圾回收器绝不会回收它。
    * 当内存空 间不足，Java虚拟机宁愿抛出OutOfMemoryError错误，使程序异常终止，
    * 也不会靠随意回收具有强引用的对象来解决内存不足问题。
    * 但是只要引用一消失，变成垃圾，直接被回收
    * */
    public static void main(String[] args) throws IOException {
        //一个强引用
        Animal animal = new Animal();
        //不在指向
        animal=null;
        //垃圾回收,animal中finalize方法执行
        System.gc();
        //输出null
        System.out.println(animal);
        //堵塞线程，给线程执行垃圾回收时间
        System.in.read();
    }
}
