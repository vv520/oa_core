package com.htwy.oa.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

//配置拦截器
@Configuration
public class Interceptorconfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //放行路径
        List<String> jwtExcludePatterns = new ArrayList();
        //springboot2.X会对静态资源进行拦截
        //系统静态资源的放行  前后端分离项目不用考虑静态资源的放行，只需要验权即可
        jwtExcludePatterns.add("/bootstrap/**");
        jwtExcludePatterns.add("/css/**");
        jwtExcludePatterns.add("/easyui/**");
        jwtExcludePatterns.add("/images/**");
        jwtExcludePatterns.add("/js/**");

        //需要放行的接口
        jwtExcludePatterns.add("/logins");
        jwtExcludePatterns.add("/captcha");

        registry.addInterceptor(new recordInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(jwtExcludePatterns);
    }
}