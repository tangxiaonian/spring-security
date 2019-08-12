package com.tang.rbac.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义登录失败的处理逻辑
 * @author ASUS
 * @create 2019-08-11 20:05
 */
@Component
public class MyAuthenticationFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        response.setHeader("content-type","text/json;charset=utf-8");

        PrintWriter writer = response.getWriter();

        writer.print("登录失败!case:"+exception.getMessage());

    }
}
