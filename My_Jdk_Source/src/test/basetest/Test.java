package test.basetest;

import com.sun.tools.javac.code.Attribute;
import test.pojo.Person;
import test.pojo.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.Instant;

public class Test {

    public static void main(String[] args) throws CloneNotSupportedException, NoSuchFieldException {
        Test test = new Test();
        System.out.println(test);
        Student student = new Student("张三", 23);
        Class clazz = student.getClass();
        Field field = clazz.getDeclaredField("username");
        field.setAccessible(true);
        student.setUsername("lisi");
        System.out.println(student.getUsername());
        Constructor[] constructors ;
        constructors=clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
            System.out.print(Modifier.toString(constructor.getModifiers())+"参数");
            System.out.println(constructor.getParameterTypes());
        }
        Instant instant  = Instant.now();
        System.out.println(instant);
    }
}

