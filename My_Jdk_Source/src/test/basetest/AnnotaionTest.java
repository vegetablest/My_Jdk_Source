package test.basetest;

import java.lang.annotation.*;

/**
 * @author suaf
 */

public class AnnotaionTest extends Object {
    public static void main(String[] args) {

    }
    /**重写的注解*/
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    @testAnnotain(name = "测试",num = 1)
    public void test(){

    }
}

/**自定义注解,注解需要元注解
 * @author bangsun*/

@Target({ElementType.METHOD,ElementType.TYPE})//设置注解使用范围，方法和类上
@Retention(RetentionPolicy.RUNTIME) //设置注解有效期间，运行时有效
@interface testAnnotain{
    String name() default "";      //自定义注解的参数,并添加默认值
    int num() default 1;      //自定义注解的参数,并添加默认值
    int sum() default -1;      //自定义注解的参数,默认值为-1代表不存在，找不到时候
    String[] strs() default {"heihei"};      //自定义注解的参数,默认值为-1代表不存在，找不到时候
}

/**自定义注解,注解需要元注解
 * @author bangsun*/
@Inherited  //使被它修饰的注解具有继承性（如果某个类使用了被@Inherited修饰的注解，则其子类将自动具有该注解）。
@Documented    //描述在使用 javadoc 工具为类生成帮助文档时是否要保留其注解信息。
@Target({ElementType.METHOD,ElementType.TYPE})//设置注解使用范围，方法和类上
@Retention(RetentionPolicy.RUNTIME) //描述注解保留的时间范围（即：被描述的注解在它所修饰的类中可以被保留到何时
@interface testAnnotain1{
    String value() default "";      //自定义注解的参数,并添加默认值,如果只有一个value时候，使用时候不用声明就行
}