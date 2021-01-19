package test.newfeatures;

import com.sun.xml.internal.xsom.impl.scd.Iterators;

import javax.lang.model.element.NestingKind;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author bangsun
 */
public class FourFuncation {

    public static void main(String[] args) {

        //
        MyFuncation myFuncation = new MyFuncation() {
            @Override
            public int myInt(String s) {
                return s.length();
            }

            @Override
            public String myString(String s) {
                return s;
            }
        };

        System.out.println(myFuncation.myInt("tyuio"));
        System.out.println(myFuncation.myString("tyuio"));

        //断定型接口,入参T返回Boolean
/*        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        };*/
        Predicate<String> predicate = s-> {return s.isEmpty();};
        System.out.println(predicate.test(""));
        //消费者接口，输入T无返回
/*        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s");
            }
        };*/
        Consumer<String> consumer = s ->{System.out.println(s);};
        consumer.accept("s");
        //供给型接口，没有输入却能返回
/*        Supplier supplier = new Supplier() {
            @Override
            public Object get() {

                return "haha";
            }
        };*/
        Supplier supplier = ()->{return "haha";};
        System.out.println(supplier.get());
    }
}

interface MyFuncation{

    public int myInt(String s);
    public String myString(String s);
}