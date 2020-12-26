package com.bsfit.suaf.staticproxy;

/**
 * @author bangsun
 */
public class UserServiceImplProxy implements UserService {

    private UserServiceImpl userService;

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }


    public void add() {
        System.out.println("静态代理增加");
        userService.add();
    }

    public void delete() {
        System.out.println("静态代理删除");
        userService.delete();
    }

    public void update() {
        System.out.println("静态代理更新");
        userService.update();
    }

    public void query() {
        System.out.println("静态代理查询");
        userService.query();
    }
}
