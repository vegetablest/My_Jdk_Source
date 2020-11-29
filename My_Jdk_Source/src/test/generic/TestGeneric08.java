package test.generic;


/**
 * @author bangsun
 */
public class TestGeneric08 implements TestGeneric07{

    /**
     * 不加泛型就是Object,隐式
     * */
    @Override
    public Object getKey() {
        return "hello";
    }
}
class TestGeneric09 implements TestGeneric07<String>{

    /**
     * 接口加泛型就是普通的接口
     * */
    @Override
    public String getKey() {
        return "hello";
    }
}
