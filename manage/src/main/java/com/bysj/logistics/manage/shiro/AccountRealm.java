//package com.bysj.logistics.manage.shiro;
//
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.stereotype.Component;
//
///**
// * <br>
// * 继承AuthorizingRealm，重写两个方法，分别是交给shiro权限验证的和自己生成token的
// * @author bangsun
// * @data 2021/4/4 16:45
// * @verson
// */
//@Component
//public class AccountRealm extends AuthorizingRealm {
//
//    @Override
//    public boolean supports(AuthenticationToken token) {
//        return token instanceof JwtToken;
//    }
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        return null;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        JwtToken token = (JwtToken)authenticationToken;
//        return null;
//    }
//}
