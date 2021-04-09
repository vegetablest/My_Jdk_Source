package com.bysj.logistics.manage.mapper;

import com.bysj.logistics.manage.pojo.MenuList;

import java.util.List;

public interface MenuListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MenuList record);

    int insertSelective(MenuList record);

    MenuList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MenuList record);

    int updateByPrimaryKey(MenuList record);

    List<MenuList> findAll();
}