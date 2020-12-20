package com.bsfit.suaf.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

public class People {
    private String name;
    @Autowired(required = false) //可用在属性上,required = false 上下文中找不到不报错，就是可以为null
    //@Qualifier(value = "cat222") //name不一样的时候使用它
    //@Resource  //java的注解，先按照name再按照type
    private Cat cat;
    private Dog dog;

    public People() {
    }

    public People(String name, Dog dog, Cat cat) {
        this.name = name;
        this.dog = dog;
        this.cat = cat;
    }

    public String getName() {
        return name;
    }
    public Dog getDog() {
        return dog;
    }

    @Autowired //可用于set方法上
    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        People people = (People) o;

        if (name != null ? !name.equals(people.name) : people.name != null) {
            return false;
        }
        if (dog != null ? !dog.equals(people.dog) : people.dog != null) {
            return false;
        }
        return cat != null ? cat.equals(people.cat) : people.cat == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (dog != null ? dog.hashCode() : 0);
        result = 31 * result + (cat != null ? cat.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", dog=" + dog +
                ", cat=" + cat +
                '}';
    }
}
