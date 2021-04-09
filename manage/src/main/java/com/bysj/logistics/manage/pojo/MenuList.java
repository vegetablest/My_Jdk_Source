package com.bysj.logistics.manage.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单列表
 * @author bangsun
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "菜单列表", description = "MenuList & Children")
public class MenuList implements Serializable {

    @ApiModelProperty(value = "id",example = "自增id")
    private Long id;

    @ApiModelProperty(value = "菜单名称",example = "员工管理")
    private String authname;
    @ApiModelProperty(value = "菜单表述",example = "员工管理的页面描述，可为null，默认为null")
    private String description;
    @ApiModelProperty(value = "子菜单节点",example = "子菜单节点是数组")
    private List<Children> children;


//    private String children;

}