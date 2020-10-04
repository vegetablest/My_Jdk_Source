package test.reference;

import test.pojo.Animal;

import java.lang.ref.WeakReference;

/**
 *  弱引用和软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。
 *  在垃圾回收器线程扫描它所管辖的内容区域的过程中，一旦发现了只具有弱引用对象，不管当前内存空间足够与否，都会回收它的内存。
 *  不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些具有弱引用的对象。
 *  弱引用可以和一个引用队列（ReferenceQueue）联合使用，如果弱引用所引用的对象被垃圾回收，
 *  Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。
 * */
public class Test03WeakReference {
    public static void main(String[] args) {
        //此时weakReference内放着一个变量指向new Animal()
        WeakReference weakReference = new WeakReference(new Animal());
        //输出 test.pojo.Animal@4554617c  指向Animal
        System.out.println(weakReference.get());
        //进行垃圾回收
        System.gc();
        //输出null
        System.out.println(weakReference.get());
        //同时输出  Animal被回收
    }
}
