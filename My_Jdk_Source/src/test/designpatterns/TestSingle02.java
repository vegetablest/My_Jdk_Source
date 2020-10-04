package test.designpatterns;

public class TestSingle02 {
    private static TestSingle02 SINGLE_02;
//    static {
//        SINGLE_02 = new TestSingle02();
//    }
    //私有化构造方法，直接初始化一个SINGLE_01
    private TestSingle02() {
    }
    //先判断是否为null，再去创建，但是线程不安全
    //如果在方法上加synchronized关键字？重量级
    //如果在new代码块上加synchronized？多线程同时进入一个等待，一个执行，等待的放行之后依旧不行会新建一个
    //双重锁检测
    public static synchronized TestSingle02 getSingle(){
        if (SINGLE_02==null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SINGLE_02=new TestSingle02();
        }
        return SINGLE_02;
    }
    //有点象CAS比较并交换
//    public static synchronized TestSingle02 getSingle(){
//        if (SINGLE_02==null) {
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (SINGLE_02 == null){
//                SINGLE_02 = new TestSingle02();
//            }
//        }
//        return SINGLE_02;
//    }
    public static void main(String[] args) {
        TestSingle02 t3=TestSingle02.getSingle();
        TestSingle02 t4=TestSingle02.getSingle();
        System.out.println(t3==t4);
    }
}
