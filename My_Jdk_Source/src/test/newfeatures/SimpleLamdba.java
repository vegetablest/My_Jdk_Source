package test.newfeatures;


public class SimpleLamdba {
    public static void main(String[] args) {

        /*匿名内部类*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.format("线程：%s",Thread.currentThread().getName()));
            }
        }).start();
        /*lamdba表达式*/
        new Thread(()-> System.out.println("haha"),"线程A").start();
        new Thread(()-> System.out.println(String.format("当前线程：%s",Thread.currentThread().getName())),"线程A").start();

        /*传参可以忽略类型,一个参数能够忽略()*/
        run((name,age)-> String.format("姓名:%s,年龄:%s",name,age));
        /*单行表达式能够省略return*/
        run((name,age)-> String.format("姓名:%s,年龄:%s",name,age));
        /*多行表达式不能够省略return*/
        run((name,age)-> {
            System.out.println("duohang");
            return String.format("姓名:%s,年龄:%s",name,age);
        });
        /*静态方法引用*/
        run(SimpleLamdba :: doFormat);
        /*普通方法引用*/
        run(new SimpleLamdba() :: doFormat2);
    }
    private static String doFormat(String s, int i) {
        System.out.println("hhhhh");
        return "name"+s+"age"+i;
    }
    private String doFormat2(String s, int i) {
        System.out.println("hhhhh");
        return "name"+s+"age"+i;
    }

    public static void run(Format format){
        format.run("suaf",23);
    }
    public interface Format{
        String run(String name,int age);
    }

}

@FunctionalInterface
interface myRun2 extends Runnable{
    /**
     * 加上改行代码就不是函数式接口,就不能用lamdba 表达式了
     * */
    //void run2();
    /**
     * 加上改行代码依旧是函数式接口,能用lamdba 表达式了
     * */
    default void run2() {
        System.out.println("默认方法");
    }

    /**
     * Object方法除外
     * */
    @Override
    String toString();
}