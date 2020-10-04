package test.designpatterns;

public class TestSingle01 {
    private static final TestSingle01 SINGLE_01;
    static {
        SINGLE_01 = new TestSingle01();
    }
    //私有化构造方法，直接初始化一个SINGLE_01
    private TestSingle01() {
    }
    public static TestSingle01 getSingle(){
        return SINGLE_01;
    }

    public static void main(String[] args) {
        TestSingle01 t1=TestSingle01.getSingle();
        TestSingle01 t2=TestSingle01.getSingle();
        System.out.println(t1==t2);
    }
}
