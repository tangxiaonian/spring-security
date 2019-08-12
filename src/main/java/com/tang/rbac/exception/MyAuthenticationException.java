package com.tang.rbac.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author ASUS
 * @create 2019-08-11 20:08
 */
public class MyAuthenticationException extends AuthenticationException {

    public MyAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public MyAuthenticationException(String msg) {
        super(msg);
    }
}
