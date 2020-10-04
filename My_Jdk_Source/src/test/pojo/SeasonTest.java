package test.pojo;
/**
 * 枚举类使用，如果枚举类只有一个对象，能够作为单例使用
 * 定义枚举类方式一，自定义定义
 * 定义枚举类方式二，enum关键字
 * */
//自定义
public class SeasonTest {
    public static void main(String[] args) {
        Season spring = Season.SPRING;
        System.out.println(spring);
    }
}
class Season{
    //1.声明对象属性，private final 修饰
    private final String seasonDesc;
    private final String seasonName;
    //2.私有化构造器，给对象属性赋值
    private Season(String seasonDesc,String seasonName){
        this.seasonDesc=seasonDesc;
        this.seasonName=seasonName;
    }
    //3.提供当前枚举类的多个对象
    public static final Season SPRING= new Season("春暖花开","春天");
    public static final Season SUMMER= new Season("夏日炎炎","夏天");
    public static final Season AUTUMN= new Season("秋高气爽","秋天");
    public static final Season WINTER= new Season("冰天雪地","冬天");
    //提供get方法，获取枚举类对象

    public String getSeasonDesc() {
        return seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonDesc='" + seasonDesc + '\'' +
                ", seasonName='" + seasonName + '\'' +
                '}';
    }
}