package com.bysj.logistics.manage.controller;

import com.bysj.logistics.manage.pojo.MenuList;
import com.bysj.logistics.manage.pojo.Result;
import com.bysj.logistics.manage.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/4/8 21:33
 * @verson
 */
@RestController
@Api(value = "菜单Controller", tags = {"菜单CURL"})
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private MenuService menuService;

    /**
     * 获取菜单
     */
    @ApiOperation(value = "全部菜单获取", notes = "菜单获取接口")
    @GetMapping("/menus")
    public Result<List<MenuList>> getMenuList() {
        List<MenuList> menuLists = menuService.findAll();
        return Result.succ(menuLists);
    }

    /**
     * 添加子菜单
     */
    @ApiOperation(value = "子菜单添加", notes = "子菜单添加")
    @PostMapping("/updatemenu")
    public Result updateMenuList(@ApiParam(name = "menuList", value = "menuList", required = true)
                              @RequestBody MenuList menuList) {
        try {
            int i = menuService.updateMenuList(menuList);
            logger.info("{}添加子菜单项{}成功，受影响{}条", menuList.getAuthname(), menuList.getChildren(), i);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("-1", "添加子菜单项失败");
        }
        return Result.succ("添加子菜单项成功！");
    }
}
