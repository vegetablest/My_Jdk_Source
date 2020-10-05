package bsfit.com;

import java.util.*;

public class Test03 {
    public static void main(String[] args) {
        Random random = new Random();
        HashMap<Student, Integer> hashMap1 = new HashMap();
        List<String> list = new ArrayList<>();
        list.add("甲");
        list.add("乙");
        list.add("丙");
        list.add("丁");
        for (int i = 0; i < 100; i++) {
            int age = random.nextInt(4) + 1;
            int num = random.nextInt(4);
            Student student = new Student(list.get(num), age);
            if (hashMap1.containsKey(student)) {
                hashMap1.put(student, hashMap1.get(student) + 1);
            } else {
                hashMap1.put(student, 1);
            }
        }
        Set<Student> students = hashMap1.keySet();
        for (Student student1 : students) {
            int sum = hashMap1.get(student1);
            System.out.println(student1.toString() + "的人有" + sum + "位");
        }




    }
}