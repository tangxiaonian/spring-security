package com.tang.rbac.config;

import com.tang.rbac.exception.MyAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 密码比对之前判断 提交的验证码是否正确
 */
@Component
public class ImageCodeAuthenticatonFilter extends OncePerRequestFilter {

    private Logger loggerFactory = LoggerFactory.getLogger(ImageCodeAuthenticatonFilter.class);

//  注入登录失败的 handler
    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
//        只对登录的 url 进行判断
        boolean flage = request.getRequestURI().contains("/login");

        if (flage) {
//          取出session中生成的 验证码结果
            String session_imagecode = (String) request.getSession().getAttribute("imagecode");
//          取出request中的 用户输入的验证码
            String request_imagecode = request.getParameter("imagecode");

            if ( session_imagecode != null && request_imagecode != null ){

                if (StringUtils.isEmpty(request_imagecode)) {

                    loggerFactory.info("验证码匹配失败!");

//                    验证码为空
                    failureHandler.onAuthenticationFailure(request,response,
                            new MyAuthenticationException("验证码为空!"));
                    return;
                }
                if(!session_imagecode.equals(request_imagecode)){

                    loggerFactory.info("验证码匹配失败!");

//                  验证码不对
                    failureHandler.onAuthenticationFailure(request,response,
                            new MyAuthenticationException("验证码错误!"));
                    return;
                }
            }
        }

        loggerFactory.info("验证码匹配请求放行！");
//        放行请求
        filterChain.doFilter(request,response);
    }
}
