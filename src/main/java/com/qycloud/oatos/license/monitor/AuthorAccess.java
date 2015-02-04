package com.qycloud.oatos.license.monitor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by jiuyuehe on 2015/1/24.
 */
public class AuthorAccess implements HandlerInterceptor {




    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ut =  httpServletRequest.getHeader("ut");
        if(ut==null){
            httpServletResponse.setStatus(501);
            PrintWriter writer = httpServletResponse.getWriter();
            writer.write("checkLoginError");
            return false;
        }
        System.out.println("---pre---");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
            throws Exception {
        System.out.println("--post--");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e)
            throws Exception {
        System.out.println("---after--");
    }
}
