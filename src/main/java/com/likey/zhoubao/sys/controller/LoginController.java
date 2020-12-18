package com.likey.zhoubao.sys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: likeyu
 * @create: 2020-11-05 09:16
 **/
@RestController
public class LoginController {

    @PostMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/success")
    public String loginSuccess(){
        return "Success";
    }

    @PostMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }
}
