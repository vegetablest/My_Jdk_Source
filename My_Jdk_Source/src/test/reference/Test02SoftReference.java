package test.reference;

import java.lang.ref.SoftReference;

/**
 * 软引用，如果一个对象只具有软引用，那就类似于可有可物的生活用品。
 * 如果内存空间足够，垃圾回收器就不会回收它，如果内存空间不足了，就会回收这些对象的内存。
 * 只要垃圾回收器没有回收它，该对象就可以被程序使用。软引用可用来实现内存敏感的高速缓存。
 * 软引用可以和一个引用队列（ReferenceQueue）联合使用，如果软引用所引用的对象被垃圾回收，
 * Java虚拟机就会把这个软引用加入到与之关联的引用队列中。
 * */
public class Test02SoftReference {
    public static void main(String[] args) {
        //放入10M的字节数组，执行之前设置虚拟机最大-Xmx20M
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024*1024*10]);
        //输出[B@4554617c
        System.out.println(softReference.get());
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //输出[B@4554617c，softReference没有被回收
        System.out.println(softReference.get());
        //再分配一个大于10M的字符数组，总内存大于20M，softReference被回收
        byte [] bytes = new byte[1024*1024*11];
        //输出null，当内存不够用的时候将软引用回收掉
        System.out.println(softReference.get());
    }
}
