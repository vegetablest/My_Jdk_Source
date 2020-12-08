package com.bsfit.suaf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * @author bangsun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Alias("haha")
public class ActorChange {

    /*该字段故意变得不一样*/
    private Integer customerId;
    private String firstName;
    private String lastName;
    private Date lastUpdate;
}



