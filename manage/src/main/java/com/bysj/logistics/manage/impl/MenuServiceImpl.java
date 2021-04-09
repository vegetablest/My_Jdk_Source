package com.bysj.logistics.manage.impl;

import com.bysj.logistics.manage.mapper.MenuListMapper;
import com.bysj.logistics.manage.pojo.MenuList;
import com.bysj.logistics.manage.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/4/8 21:37
 * @verson
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuListMapper menuListMapper;


    @Override
    public List<MenuList> findAll() {
        return menuListMapper.findAll();
    }

    @Override
    public MenuList getMenuListById(Long id) {
        return menuListMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateMenuList(MenuList menuList) {
        return menuListMapper.updateByPrimaryKeySelective(menuList);

    }

    @Override
    public void insertMenuList(MenuList menuListById) {

    }
}
