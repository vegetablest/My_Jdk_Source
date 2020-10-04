package test.juctest;

public class Test10_Thread {
    public static void main(String[] args) {
        Thread thread = new Thread();
        //State是Thread里的一个枚举类有六种状态NEW、RUNNABLE、BLOCKED、WAITING、TIMED_WAITING、TERMINATED
        //输出NEW，刚刚创建这个线程
        System.out.println(thread.getState());
        thread.start();
        //输出RUNNABLE,线程运行了
        System.out.println(thread.getState());
        //thread.destroy();
        //线程已经销毁，查不到状态
        //System.out.println(thread.getState());
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
    }
}
