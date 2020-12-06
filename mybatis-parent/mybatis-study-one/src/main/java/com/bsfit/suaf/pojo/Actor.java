package com.bsfit.suaf.pojo;

import lombok.*;

import java.util.Date;

/**
 * @author bangsun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actor {

    private Integer actorId;
    private String firstName;
    private String lastName;
    private Date lastUpdate;

}
