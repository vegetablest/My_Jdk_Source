package com.bysj.logistics.manage.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/4/8 21:26
 * @verson
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "子级菜单列表", description = "Children")
public class Children implements Serializable {
    @ApiModelProperty(value = "id",example = "自增id")
    private String id;
    @ApiModelProperty(value = "路径",example = "菜单路径")
    private String path;
    @ApiModelProperty(value = "菜单名称",example = "菜单名称")
    private String authName;
}
