package com.tang.rbac.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义权限不足 处理的路径
 * @author ASUS
 * @create 2019-08-10 18:02
 */
public class MyAccessDeniedeHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setHeader("Content-Type", "text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        response.getWriter().print("亲,你这边没有权限呐!");

    }
}
