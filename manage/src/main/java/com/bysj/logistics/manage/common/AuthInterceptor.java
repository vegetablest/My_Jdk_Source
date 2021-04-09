package com.bysj.logistics.manage.common;
import com.bysj.logistics.manage.pojo.Result;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * <br>
 * 拦截器
 * @author bangsun
 * @data 2021/4/4 17:45
 * @verson
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Object user = request.getSession().getAttribute("username");
        if (user != null) {
            request.getSession().setAttribute("username", user);
            return true;
        }
        response.getWriter().print(Result.fail("NoLogin"));
        response.sendRedirect("/user/login.html");
        return false;
    }
}
