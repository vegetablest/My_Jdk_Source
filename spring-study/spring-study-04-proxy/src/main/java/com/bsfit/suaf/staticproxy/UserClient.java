package com.bsfit.suaf.staticproxy;

/**
 * @author bangsun
 */
public class UserClient {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        UserServiceImplProxy userServiceImplProxy = new UserServiceImplProxy();
        userServiceImplProxy.setUserService(userService);
        userServiceImplProxy.add();
        userServiceImplProxy.delete();
        userServiceImplProxy.update();
        userServiceImplProxy.query();
    }


}
