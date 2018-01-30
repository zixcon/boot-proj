package com.holdon.user.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wd on 2018/1/24.
 */
public class AuthTokenIntercepter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        //如果 session
        // String token = request.getHeader("x-auth-token");
        // String token = httpSession.getId();
//        String loginName = (String) httpSession.getAttribute("loginName");
//        if (null == loginName || "".equals(loginName)) {
//            this.wirteNoLoginResponse(request, response);
//            return false;
//        } else {
//            // 获取url，ip 地址对其进行权限校验
//        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    private void wirteNoLoginResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write("{'result':false,'code':10000,'message':\"用户未登陆\"}");
        printWriter.flush();
    }
}
