package pojo;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import java.util.Date;
/*注意：FastJson 在进行操作时，是根据 getter 和 setter 的方法进行的，并不是依据 Field 进行。
        注意：若属性是私有的，必须有 set 方法。否则无法反序列化。
        可以配置在 getter/setter 方法或者字段上,也可以配置在 getter/setter 方法或者字段上*/
@Data
//lomback自动生成构造
public class Person {

    //默认情况下， FastJson 库可以序列化 Java bean 实体， 但我们可以使用 serialize 指定字段不序列化。
    @JSONField(name = "AGE", serialize=false)
//    @JSONField(name = "DATE OF BIRTH", deserialize=false) //指定不反序列化
    private int age;
    //使用 ordinal 参数指定字段的顺序
    @JSONField(name = "FULL NAME",ordinal = 2)
    private String fullName;
    //  format 参数用于格式化 date 属性。
//    @JSONField(name = "DATE OF BIRTH", format="dd/MM/yyyy", ordinal = 3)
    @JSONField(name = "DATE OF BIRTH", ordinal = 3)
    private Date dateOfBirth;

    public Person(int age, String fullName, Date dateOfBirth) {
        super();
        this.age = age;
        this.fullName= fullName;
        this.dateOfBirth = dateOfBirth;
    }
    // 标准 getters & setters
}