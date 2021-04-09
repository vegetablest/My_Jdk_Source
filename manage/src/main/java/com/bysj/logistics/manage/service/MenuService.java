package com.bysj.logistics.manage.service;

import com.bysj.logistics.manage.pojo.MenuList;

import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/4/8 21:37
 * @verson
 */
public interface MenuService {

    List<MenuList> findAll();

    MenuList getMenuListById(Long id);

    int updateMenuList(MenuList menuList);

    void insertMenuList(MenuList menuListById);
}
