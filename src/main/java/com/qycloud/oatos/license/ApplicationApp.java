package com.qycloud.oatos.license;

import com.qycloud.oatos.license.monitor.AuthorAccess;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jiuyuehe on 2015/1/16.
 */


@SpringBootApplication
public class ApplicationApp extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // super.addInterceptors(registry);
        registry.addInterceptor(new AuthorAccess()).addPathPatterns("/sc/**");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationApp.class, args);
    }
}
