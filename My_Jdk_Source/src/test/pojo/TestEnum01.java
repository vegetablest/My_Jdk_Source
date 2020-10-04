package test.pojo;

/**
 * 枚举类型：很早就出现在编程语言中，它被用来将一组类似的值包含到一种类型当中。
 * 而这种枚举类型的名称则会被定义成独一无二的类型描述符，在这一点上和常量的定义相似。
 * 不过相比较常量类型，枚举类型可以为申明的变量提供更大的取值范围。
 *
 * 类型不安全、没有命名空间，只能通过类访问
 * 一致性差，每次更新都需要新编译、类型无指意性，只有自己明白，别人不明白，可读性差
 *
 * 编译器在编译的时候自动添加的values方法，并且编译器也添加了另外一个方法valueOf方法，
 * 这个是否是Enum类中valueOf方法的重载。在Enum类中，valueOf方法有两个参数，自动生成的只有一个参数。
 * */
public enum TestEnum01 {
    SPRING("春天"),SUMMER("夏天"),FALL("秋天"),WINTER("冬天");
    private String name;

    public String getName() {
        return name;
    }

    TestEnum01(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestEnum01{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        for (TestEnum01 value : values()) {
            //enum的名字SPRING,SUMMER
            System.out.println(value.name());
            System.out.println(value);
        }
    }
}
