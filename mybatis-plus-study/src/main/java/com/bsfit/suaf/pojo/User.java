package com.bsfit.suaf.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.IDLType;

@Data
@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class User {
    /*主键生成策略，redis,zookeeper，雪花算法，自增ID，uuid*/
    @TableId(type = IdType.AUTO)
    private long id;
    private String name;
    private Integer age;
    private String email;
}
