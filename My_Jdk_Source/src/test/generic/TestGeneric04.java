package test.generic;

/**
 * 泛型类派生子类泛型一致
 * @author bangsun
 */
public class TestGeneric04<T> extends TestGeneric03<T> {

    @Override
    public T getProduct() {
        return super.getProduct();
    }
}
/**
 * 子类可继续多定义一些泛型
 * */
class TestGeneric05<T,E,K,V> extends TestGeneric03<T> {}
/**
 * 子类可不加泛型，就是普通类，但父类要声明泛型类型
 * */
class TestGeneric06 extends TestGeneric03<String> {}

