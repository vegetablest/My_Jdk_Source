package test.basetest;

/**
 * new Object对象的创建过程
 */
public class TestNewObject {
    public static void main(String[] args){
        Object object = new Object();
        //可以通过反射去获取该类模板的根加载器，输出为null，因为他是根加载器，C语言写的，其他加载器继承他
        System.out.println(object.getClass().getClassLoader());
        //报错，根加载器他没有父类
        //System.out.println(object.getClass().getClassLoader().getParent());
        TestNewObject myobject = new TestNewObject();
        System.out.println(myobject.getClass().getClassLoader());
        System.out.println(myobject.getClass().getClassLoader().getParent());
        System.out.println(myobject.getClass().getClassLoader().getParent().getParent());
    }
}
