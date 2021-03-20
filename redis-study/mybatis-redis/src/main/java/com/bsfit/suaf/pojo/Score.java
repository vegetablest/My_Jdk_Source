package com.bsfit.suaf.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/3/19 23:10
 * @verson
 */
@Data
@Accessors(chain = true)
public class Score implements Serializable {
    private String sId;
    private String cId;
    private int sScore;

}
