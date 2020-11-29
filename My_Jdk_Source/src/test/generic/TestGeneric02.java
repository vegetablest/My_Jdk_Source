package test.generic;

/**
 * 泛型类的声明  可以理解为形参
 * @author bangsun {@link TestGeneric01}
 */
public class TestGeneric02<T> {

    public static void main(String[] args) {
        TestGeneric02<String> generic02 = new TestGeneric02<>("a");
        /*直接返回string类型*/
        String key = generic02.getKey();
        System.out.println("key = " + key);
        TestGeneric02<Integer> generic03 = new TestGeneric02<>(10);

        /*直接返回string类型*/
        Integer key2 = generic03.getKey();
        System.out.println("key2 = " + key2);

        /*默认Object类型*/
        TestGeneric02 generic04 = new TestGeneric02(10);
        Object key3 = generic03.getKey();
        System.out.println("key3 = " + key3);

        /*不支持基本数据类型类型*/
//        TestGeneric02<int> generic05 = new TestGeneric02(10);
        TestGeneric02<Integer> generic06 = new TestGeneric02(10);
    }

    /**
     * T是由外部使用的时候进行判断
     * */
    private T key;


    public TestGeneric02(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "TestGeneric02{" +
                "key=" + key +
                '}';
    }
}
