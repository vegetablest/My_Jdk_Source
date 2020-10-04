package test.pojo;
/**
 * 使用关键字进行设置
 * 说明定义的枚举类
 * */
public class SeasonTest01 {
    public static void main(String[] args) {
        Season1 season1 = Season1.SPRING;
        System.out.println(season1);
        for (Season1 value : Season1.values()) {
            System.out.println(value);
        }
        System.out.println(Season1.valueOf("SPRING"));
        Thread.State[] state = Thread.State.values();
        System.out.println(state);
    }
}
enum Season1{
    //1.提供当前枚举类的对象，多个对象之间用，隔开，省去new和public static final Season SPRING
    //public static final Season SPRING= new Season("春暖花开","春天");
    SPRING("春暖花开","春天"),
    SUMMER("夏日炎炎","夏天"),
    AUTUMN("秋高气爽","秋天"),
    WINTER("冰天雪地","冬天");

    //1.声明对象属性，private final 修饰
    private final String seasonDesc;
    private final String seasonName;
    //2.私有化构造器，给对象属性赋值
    private Season1(String seasonDesc,String seasonName){
        this.seasonDesc=seasonDesc;
        this.seasonName=seasonName;
    }
       //提供get方法，获取枚举类对象

    public String getSeasonDesc() {
        return seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }
//    不重写tostring方法时候打印的是enum的属性名，因为Enum自带了一些方法 点进去查看

    @Override
    public String toString() {
        return "Season1{" +
                "seasonDesc='" + seasonDesc + '\'' +
                ", seasonName='" + seasonName + '\'' +
                '}';
    }
}