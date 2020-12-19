package com.bsfit.suaf.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class User {
    /*主键生成策略，redis,zookeeper，雪花算法，自增ID，uuid*/
    /*
     * AUTO(0)数据库id自增
     * NONE(1)未设置主键
     * INPUT(2)手动输入
     * ID_WORKER(3)全局默认唯一id
     * UUID(4)全局唯一uuid
     * ID_WORLKER_STR(5)字符串表示法
     * */
    @TableId(type = IdType.AUTO)
    private long id;
    private String name;
    private Integer age;
    /*字段自动处理注解，写个handler去实现怎么对字段自动处理的逻辑*/
    @TableField(fill = FieldFill.INSERT)
    private Date insertTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version;

    /*新版本yml配置之后不用注解就行*/
    @TableField(fill = FieldFill.INSERT)
    private Integer delete;

}
