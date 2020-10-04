/*
 * Copyright (c) 1994, 2012, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package java.lang;

/**
* Object所有Java类的基类，自带native方法去调用操作系统方法
* */
public class Object {

    private static native void registerNatives();
    static {
        registerNatives();
    }

    /**
     * static synchronized 方法也是锁定的也是返回的这个类
     *
     * 返回运行时类的类名，class + 包名.类名
     * eg:  class test.basetest.Test
     */
    public final native Class<?> getClass();

    /**
     *哈希码并不是完全唯一的，它是一种算法，让同一个类的对象按照自己不同的特征
     *尽量的有不同的哈希码，但不表示不同的对象哈希码完全不同，可能会产生hash碰撞
     *hash值是根据对象在内存中的地址进行计算得到的一串值
     */
    public native int hashCode();

    /**
     *equals底层方法是==，==比较的内存中的地址值，我们可以想到hashcode就是根据地址值进行计算得到的，
     * 那么只要equals相等的两个对象，hashcode是不是也相等？答案是一定的，由此我们能够想到
     * 两个对象==，那么得到的hashcode一定相等，两个hashcode相等未必是同一对象（hash碰撞）；
     * 所以以后我们要做到：
     * 重写equals一定要重写hashcode
     * 重写hashcode不一定要重写equals，最好全部重写
     */
    public boolean equals(Object obj) {
        return (this == obj);
    }

    /**
     *克隆此对象生成一个新对象，但是该对象不是原来的对象，该方法是protected方法
     * 二者经过==比较不同，测试之前要想让一个类能够clone需要实现Cloneable接口
     * public class Student implements Cloneable
     * 输出：
     * test.basetest.Student@4554617c
     * test.basetest.Student@74a14482
     * 23
     * 张三
     * 如果里边是引用类型呢？此处未重写equals
     * System.out.println(student3.getPerson().equals(student2.getPerson()));
     * true
     *可见他就像python里边的浅拷贝一样，只拷贝第一层和基本数据类型。
     */
    protected native Object clone() throws CloneNotSupportedException;

    /**
     * 默认输出返回该对象的字符串表示。通常，toString 方法会返回一个“以文本方式表示”此对象的字符串。
     * 结果应是一个简明但易于读懂的信息表达式。建议所有子类都重写此方法。
     * Object 类的 toString 方法返回一个字符串，该字符串由类名（对象是该类的一个实例）、at
     * 标记符“@”和此对象哈希码的无符号十六进制表示组成。换句话说，该方法返回一个字符串，它的值等于：
     * test.basetest.Student@1540e19d
     *
     */
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    /**
     * 往下看wait()、notify、notifyAll() 方法是Object的本地final方法，无法被重写
     * 线程间的线程间通信的模型有两种：共享内存和消息传递，下边三个方法就是为了线程间的通信的基础。
     * JUC的测试在JUCTest包内共五种方法实现线程间通信
     * 1.wait 2.volatile 3.ReentrantLock  4.CountDownLatch 5.LockSupport
     * */
    /**
     * 唤醒该对象监视的正在等待的单个线程，如果有多个线程正在等待，则随机唤醒一个线程。
     * notify/notifyAll() 的执行只是唤醒沉睡的线程，而不会立即释放锁，锁的释放要看代码块的具体执行情况。
     * 所以在编程中，尽量在使用了notify/notifyAll() 后立即退出临界区，以唤醒其他线程让其获得锁
     */
    public final native void notify();


    public final native void notifyAll();

    /**
     * 一般与synchronized共同出现，用来停止该线程让出该线程的锁，让出该线程的CPU执行权，等待其他notify或者notifyAll唤醒
     * 当线程被唤醒之后重新获得锁，所以在使用中最好加上try catch 以便发生意外依旧能被唤醒。
     */
    public final native void wait(long timeout) throws InterruptedException;

    /**
     * 有参构造，等待的时长
     */
    public final void wait(long timeout, int nanos) throws InterruptedException {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                                "nanosecond timeout value out of range");
        }

        if (nanos > 0) {
            timeout++;
        }

        wait(timeout);
    }

    /**
     * 无参方法
     */
    public final void wait() throws InterruptedException {
        wait(0);
    }

    /**
     * 建议进行垃圾回收，但是仅仅是建议，并不会因为该方法直接进行垃圾回收，很小概率触发垃圾回收
     * 垃圾回收很大程度上取决与系统JVM自己判断，四大垃圾回收算法，七大垃圾回收机制
     */
    protected void finalize() throws Throwable { }
}
