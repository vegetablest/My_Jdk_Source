package test.collectiontest;



import test.pojo.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * 重写hash和重写equals对hashmap的put数据有什么影响？
 * */
public class TestHashMap01 {
    public static void main(String[] args) {
        Student student01 = new Student("张三",23);
        Student student02 = new Student("张三",23);
        Student student03 = new Student("lisi",23);
        HashMap<Student,Integer> hashMap = new HashMap<>();
        List<Student> list = new ArrayList<>();
        list.add(student02);
        list.add(student03);
        int num = 0;
        hashMap.put(student01,num++);
        for (Student student : list) {
            if (hashMap.containsKey(student)){
                int sum =hashMap.put(student,num++);
                System.out.println("当hashmap包含key时他会返回一个值:"+sum);
                System.out.println(student.hashCode()==student01.hashCode());
                System.out.println(student.equals(student01));
            }else {
                //报错
                //int sum =hashMap.put(student,num++);
                System.out.println("当hashmap不包含key时他不会返回一个值:");
                System.out.println(student.hashCode()==student01.hashCode());
                System.out.println(student.equals(student01));
            }
        }
    }
}
