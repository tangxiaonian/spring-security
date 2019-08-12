package com.tang.rbac.config;

import com.tang.rbac.Utils.ImageCodeServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ASUS
 * @create 2019-08-11 19:20
 */
@Configuration
public class RootConfig {

    /**
     * 定义图片验证码的servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean<ImageCodeServlet> registrationBean() {

        ServletRegistrationBean<ImageCodeServlet> servletRegistrationBean = new ServletRegistrationBean<>();

        servletRegistrationBean.addUrlMappings("/imageCode");
        servletRegistrationBean.setServlet( new  ImageCodeServlet());
        servletRegistrationBean.setName("servletRegistrationBean");

        return servletRegistrationBean;
    }

}
