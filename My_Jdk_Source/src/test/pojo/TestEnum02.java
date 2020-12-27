package test.pojo;
/**
 * 最早没有出枚举类的时候就是这样写，出完枚举类   由
 * private static TestEnum02 SPRING = new TestEnum02("春天");简化为SPRING("春天");
 * */
public class TestEnum02 {
    //枚举私有化new出来，可以给final修饰
    private static final TestEnum02 SPRING = new TestEnum02("春天");
    private static final TestEnum02 SUMMER = new TestEnum02("夏天");
    private static final TestEnum02 FALL = new TestEnum02("秋天");
    private static final TestEnum02 WINTER = new TestEnum02("冬天");

    //声明属性，私有化
    private final String name;

    //构造方法私有化
    private TestEnum02(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestEnum02{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(TestEnum02.SPRING);
    }
}
