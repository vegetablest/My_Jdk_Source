//package com.bysj.logistics.manage.shiro;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * <br>
// *
// * @author bangsun
// * @data 2021/4/4 17:10
// * @verson
// */
//@Component
//public class JwtFilter extends AuthenticatingFilter {
//    @Override
//    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        String jwt = request.getHeader("Authorization");
//        return StringUtils.isEmpty(jwt) ? null : new JwtToken(jwt);
//    }
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        String jwt = request.getHeader("Authorization");
//        /*没有jwt不拦截，交给注解拦截*/
//        if (StringUtils.isEmpty(jwt)) {
//            return true;
//        } else {
//            //校验
//
//            //执行登录
//        }
//        return false;
//    }
//}
