package com.dr.utile;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("拦截器");
        //获取单点类中session
        String name = (String) request.getSession().getAttribute("name");
        System.out.println(name);
        if(name==null){
            System.out.println("通过失败");
            httpServletResponse.sendRedirect("login_signup.html");
            return false;
        }else {
            System.out.println("通过");
            return true;
        }


    }
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
