package test.generic;

/**
 * 泛型接口
 * 实现类不是泛型类,接口要明确数据类型
 * 实现类也是泛型类，实现类和接口的泛型类型要一致
 * @author bangsun
 */
public interface TestGeneric07<T> {
    T getKey();
}
