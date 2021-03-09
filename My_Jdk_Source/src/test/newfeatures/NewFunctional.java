package test.newfeatures;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/9 11:02
 * @verson
 */
public class NewFunctional {
    private int age;
    private String name;

    public NewFunctional() {
    }

    public NewFunctional(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public static void main(String[] args) {
        /**::怎么用？  构造静态函数引用*/
        Function<String,Integer> function = Integer::parseInt;
        Integer apply = function.apply("3");
        System.out.println("apply = " + apply);

        /**::怎么用？  普通方法函数引用*/
        String jdk8 = "Hello JDK8";
        Function<Integer,String> function1 = jdk8::substring;
        String apply1 = function1.apply(1);
        System.out.println("apply1 = " + apply1);

        /**::怎么样？  构造方法引用*/
        BiFunction<Integer,String,NewFunctional> biFunction =  NewFunctional::new;
        NewFunctional lisi = biFunction.apply(18, "lisi");
        System.out.println("lisi = " + lisi);

        /**作为方法参数*/
        sayHell(String::toUpperCase,"hello World");


    }

    private static void sayHell(Function<String,String> function,String str){
        String apply = function.apply(str);
        System.out.println(apply);
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NewFunctional{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NewFunctional that = (NewFunctional) o;

        if (age != that.age) {
            return false;
        }
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
