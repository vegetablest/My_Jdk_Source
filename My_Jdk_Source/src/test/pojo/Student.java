package test.pojo;

import java.util.Objects;

public class Student implements Cloneable {

    private Person person;
    private String username;
    private int age;

    public static void main(String[] args) throws CloneNotSupportedException {
        Student student = new Student("张三",23);
        System.out.println(student);
        Student student1 = (Student) student.clone();
        System.out.println(student1);
        System.out.println(student.getAge());
        System.out.println(student.getUsername());
        Person person= new Person();
        Student student2 = new Student(person,"张三",23);
        Student student3 = (Student) student2.clone();
        System.out.println(student3.getPerson().equals(student2.getPerson()));

        System.out.println(student3.toString());
        System.out.println(student3);
    }

    public Student(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Student(Person person, String username, int age) {
        this.person = person;
        this.username = username;
        this.age = age;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(username, student.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, age);
    }
}
