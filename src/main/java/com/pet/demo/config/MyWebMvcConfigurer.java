package com.pet.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        图片的本地上传路径
//        registry.addResourceHandler("/upload/**").addResourceLocations("file:E:/upload/");
//    }
//注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        添加拦截路径
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/backstage/admin","/Apply/find","/PetTest/pet","/front/user","/manage","/info");
    }
    //启动时访问index，而不是index.html
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    registry.addViewController("/").setViewName("forward:/index");
    registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    WebMvcConfigurer.super.addViewControllers(registry);
    }

}

