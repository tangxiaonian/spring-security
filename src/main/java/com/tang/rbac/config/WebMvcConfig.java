package com.tang.rbac.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ASUS
 * @create 2019-08-10 15:57
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

//        get login 請求 返回登录页面
        registry.addViewController("/login").setViewName("login.html");
        registry.addViewController("/addProduct").setViewName("/product/add.html");
        registry.addViewController("/deleteProduct").setViewName("/product/delete.html");
        registry.addViewController("/queryProduct").setViewName("/product/query.html");
        registry.addViewController("/updateProduct").setViewName("/product/update.html");
    }
}
