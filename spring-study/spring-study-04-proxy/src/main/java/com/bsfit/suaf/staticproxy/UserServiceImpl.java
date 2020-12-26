package com.bsfit.suaf.staticproxy;

/**
 * @author bangsun
 */
public class UserServiceImpl implements UserService{
    public void add() {
        System.out.println("增加一个用户");
    }

    public void delete() {
        System.out.println("删除一个用户");
    }

    public void update() {
        System.out.println("更改一个用户");
    }

    public void query() {
        System.out.println("查找一个用户");
    }
}
