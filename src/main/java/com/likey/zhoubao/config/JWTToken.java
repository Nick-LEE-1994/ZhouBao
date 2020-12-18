package com.likey.zhoubao.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @description:
 * @author: likeyu
 * @create: 2020-11-16 17:22
 **/
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }
    //用户名密码都是token？
    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
