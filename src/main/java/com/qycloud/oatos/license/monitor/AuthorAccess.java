package com.qycloud.oatos.license.monitor;

import com.qycloud.oatos.license.service.UserService;
import com.qycloud.oatos.license.utils.LicSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by jiuyuehe on 2015/1/24.
 */
@Component
public class AuthorAccess implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String,String> template;



    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String ut =  httpServletRequest.getHeader("ut");
        System.out.println("ut = "+ut);
        if(ut==null){
            httpServletResponse.setStatus(501);
            PrintWriter writer = httpServletResponse.getWriter();
            writer.write("checkLoginError");
            return false;
        }else{
            System.out.println(this.template);
            System.out.println(this.template);
            System.out.println(this.template.opsForValue());

            String key = new String(LicSecurity.decryptBASE64(ut));

            String userStr = this.template.opsForValue().get(key);

            System.out.println(userStr);
            if(userStr==null){
                httpServletResponse.setStatus(501);
                PrintWriter writer = httpServletResponse.getWriter();
                writer.write("checkLoginError");
                return false;
            }
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
