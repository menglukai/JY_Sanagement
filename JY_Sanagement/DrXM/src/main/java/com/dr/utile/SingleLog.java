package com.dr.utile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
/**
 *@描述   j将用户名和session绑定，实现单点
 *@创建人 zlc
 *@创建时间 2019/11/21 23:26
 *@修改人和其它信息
 */
public class SingleLog  extends HttpServlet {
    public static final Map<String,String> usermap=new HashMap();
    public static final Map<String,HttpSession> sessionrmap=new HashMap();
    //初始化创建session
    public static void init(String name,HttpServletRequest request){
        usermap.put(name,request.getSession().getId());
        sessionrmap.put(request.getSession().getId(),request.getSession());
    }
    //销毁session
    public static void destory(String name){
        sessionrmap.get( usermap.get(name)).invalidate();
        sessionrmap.remove(usermap.get(name));
        usermap.remove(name);


    }
}
