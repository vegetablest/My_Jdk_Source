package test.newfeatures;

public class TestLambda02 {
    public static void main(String[] args) {
        Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("hello");
            }
        };
        foo.sayHello();
        System.out.println(foo.add(1,2));
        System.out.println(Foo.asd(5, 3));
        //lambda表达式只能用一个方法的接口，即函数式接口@FunctionalInterface
        //拷贝小括号，写死右箭头，落地大括号
        Foo foo1 = () -> {System.out.println("hello lambda");};
        foo1.sayHello();
        Foo foo2 = () -> System.out.println("hello !!!");
        foo2.sayHello();
    }
}
//@FunctionalInterface注解，隐式注解，一个方法的接口底层默认有
interface  Foo{
    public void sayHello();

    //1.8接口新特性，加默认方法，可写多个,定义加实现
    public default  int add(int x,int y){
        return x+y;
    };
    //静态方法,可定义接口
    public static int asd(int x,int y){
        return x*y;
    }
}