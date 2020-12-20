package com.bsfit.suaf.pojo;


public class User {

    private String name;

    public User() {
        System.out.println("user的无参构造方法");
    }

    public User(String name) {
        System.out.println("有参构造生成bean");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
