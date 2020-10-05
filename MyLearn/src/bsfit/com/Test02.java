package bsfit.com;

import java.util.HashMap;
import java.util.Objects;

public class Test02 {
    public static void main(String[] args) {
        Person person01 = new Person("张三", 23);
        Person person02 = new Person("张三", 23);
        //不重写hash和equals方法，返回false,false
        //重写hash和equals方法，返回true,true
        //重写equals不重写hash,返回true,false
        //重写hash不重写equals，返回false，true
        /*结论，hash和equals本质上是比较地址值的hash值和地址值。
        通过hashmap中先比较hash值再比较equals值，反推
        既然会形成链表说明有些情况下hash值相同equals值未必相同
        当equals值相同时hash值也一定相同。
        */
        System.out.println(person01.equals(person02));
        System.out.println(person01.hashCode() == person02.hashCode());
        /*     <li>If two objects are equal according to the {@code equals(Object)}
         *     method, then calling the {@code hashCode} method on each of
         *     the two objects must produce the same integer result.
               如果根据{@code equals（Object）}两个对象相等
                *方法，然后在每个方法上调用{@code hashCode}方法
                *两个对象必须产生相同的整数结果。*/
        /*
        *   bsfit.com.Person@1b6d3586
            bsfit.com.Person@4554617c
            460141958
            1163157884
        * */
        System.out.println(person01);
        System.out.println(person02);
        System.out.println(person01.hashCode());
        System.out.println(person02.hashCode());
//        System.out.println("bsfit.com.Person@1b6d3586".hashCode());
//        System.out.println("bsfit.com.Person@4554617c".hashCode());
//        System.out.println(person01.getClass().hashCode());
//        System.out.println(person02.getClass().hashCode());
        Student student01 = new Student("张三", 23);
        Student student02 = new Student("张三", 23);
        Student student03 = new Student("张三", 23);
        Student student04 = new Student("lisi",24);
        int num = 0;
        HashMap<Student, Integer> hashMap = new HashMap<>();
        hashMap.put(student01, num);
        int num01 = hashMap.put(student02, num +=1);
        System.out.println(num01);
        int num02 = hashMap.put(student03, num +=1);
        System.out.println(num02);
        //hashMap.put(student04,3);
        int num03 = hashMap.put(student04, num +=1);
        //System.out.println(num03);
        System.out.println(student01.equals(student02) && student02.equals(student03));
        //把student01，02已经挤出来了，但是还是包含student01，02，03，说明三者hash值相同
        System.out.println(hashMap.containsKey(student01));
        System.out.println(hashMap.containsKey(student02));
        System.out.println(hashMap.containsKey(student03));
        //结果hash值就是相同
        System.out.println(student01.hashCode());
        System.out.println(student02.hashCode());
        System.out.println(student03.hashCode());

    }
}

class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    @Override
//    public String toString() {
//        return "Person{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Person person = (Person) o;
//        return age == person.age &&
//                Objects.equals(name, person.name);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(name, age);
//    }
}

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "姓名为'" + name + '\'' +
                ", 年龄为" + age ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}