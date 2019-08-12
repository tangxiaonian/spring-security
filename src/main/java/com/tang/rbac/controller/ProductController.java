package com.tang.rbac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ASUS
 * @create 2019-08-10 16:54
 */
@Controller
public class ProductController {

    /**
     * 登录成功  跳转的页面，必须是 post  因为请求是转发过来的
     * @return
     */
//    @PostMapping("/loginSucc")
//    public String LoginSuccess() {
//        return "list.html";
//    }
    //        主页面
    @GetMapping("/loginSucc")
    public String LoginSuccessGet() {
        return "list.html";
    }

}
