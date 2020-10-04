package bsfit.com;

import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.util.*;

public class Test03 {
    public static void main(String[] args) {
//        Student student01 = new Student("张三",23);
//        Student student02 = new Student("张三",23);
//        Student student03 = new Student("lisi",23);
//        HashMap<Student,Integer> hashMap = new HashMap<>();
//        List<Student> list = new ArrayList<>();
//        list.add(student02);
//        list.add(student03);
//        int num = 0;
//        hashMap.put(student01,num++);
//        for (Student student : list) {
//            if (hashMap.containsKey(student)){
//                int sum =hashMap.put(student,num++);
//                System.out.println("当hashmap包含key时他会返回一个值:"+sum);
//                System.out.println(student.hashCode()==student01.hashCode());
//                System.out.println(student.equals(student01));
//            }else {
//                //报错
//                //int sum =hashMap.put(student,num++);
//                System.out.println("当hashmap不包含key时他不会返回一个值:");
//                System.out.println(student.hashCode()==student01.hashCode());
//                System.out.println(student.equals(student01));
//            }
//        }

        Random random = new Random();
        HashMap<Student,Integer> hashMap1 = new HashMap();
        for (int i =0;i<100;i++){
            int age = random.nextInt(4);
            Student student = new  Student("张三",age);
            if(hashMap1.containsKey(student)){
                hashMap1.put(student,hashMap1.get(student)+1);
            }else {
                hashMap1.put(student,1);
            }
        }
        Set<Student> students = hashMap1.keySet();
        for (Student student : students) {
            int num = hashMap1.get(student);
            System.out.println("有"+num +"个人是：" + student.toString() );
        }

    }
}
