package test.reference;

import test.pojo.Animal;

/**
 * 内部是弱引用，极大程度上减小了内存泄漏问题，因为弱引用即使引用也会被垃圾回收。不用的时候记得remove
 * */
public class Test05ThreadLocal {
    public static ThreadLocal<M> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        new Thread(()->{
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //输出null，
            System.out.println(threadLocal.get());
            //threadLocal.remove();//remove的就是map中以this为key的entry
        }).start();
        new Thread(()->{
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //查看set方法的源码发现就是将M放入当前线程的一个ThreadLocalMap中这个map是key为this即当前线程,value为M，
            // 即当前线程的ThreadLocal，所有上边获取不到，而这个线程中ThreadLocalMap继承了WeakReference
            //threadlocal里面使用了一个存在弱引用的map,当释放掉threadlocal的强引用以后,map里面的value却没有被回收.而这块value永远不会被访问到了. 所以存在着内存泄露.
            //最好的做法是将调用threadlocal的remove方法.把当前ThreadLocal从当前线程的ThreadLocalMap中移除。(包括key，value)
            //比如使用线程池的时候，线程结束是不会销毁的，会再次使用的。就可能出现内存泄露。
            threadLocal.set(new M());
        }).start();

    }
    static class M{
        String name = "dog";
    }
}
/**
 *     public void set(T value) {
 *         Thread t = Thread.currentThread(); //当前线程
 *         ThreadLocalMap map = getMap(t);
 *         if (map != null)
 *             map.set(this, value);  //this threadlocal自己
 *         else
 *             createMap(t, value);
 *     }
 * */