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
public class Actor {

    private Integer actorId;
    private String firstName;
    private String lastName;
    private Date lastUpdate;

}
