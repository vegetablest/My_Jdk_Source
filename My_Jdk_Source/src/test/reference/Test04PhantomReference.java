package test.reference;

import test.pojo.Animal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * “虚引用”顾名思义，就是形同虚设的意思，与其他几种引用都不同，虚引用并不会决定对象的生命周期。
 * 如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收。
 * 虚引用主要用来跟踪对象被垃圾回收的活动。虚引用与软引用和弱引用的一个区别在于：
 * 虚引用必须和引用队列（ReferenceQueue）联合使用。当垃圾回收器准备回收一个对象时，
 * 如果发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之关联的引用队列中。
 * 程序可以通过判断引用队列中是否已经加入了虚引用，来了解被引用的对象是否将要被垃圾回收。
 * 程序如果发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象的内存被回收之前采取必要的行动。
 * */
public class Test04PhantomReference {
    private static final List<Object> LIST=new LinkedList<>();
    private static final ReferenceQueue<Animal> QUEUE = new ReferenceQueue<>();
    //执行前设置Xmx=20M
    public static void main(String[] args) {
        PhantomReference phantomReference = new PhantomReference(new Animal(),QUEUE);
        System.out.println(phantomReference.get());
        new Thread(()->{
            while (true){
                LIST.add(new byte[1024*1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            while (true){
                Reference<? extends Animal> poll = QUEUE.poll();
                if (poll != null ){
                    System.out.println("虚引用被垃圾回收了" + poll);
                }
            }
        }).start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
