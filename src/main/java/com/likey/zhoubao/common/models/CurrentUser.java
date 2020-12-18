package com.likey.zhoubao.common.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description: 当前用户, 用线程变量存储
 * @author: likeyu
 * @create: 2020-12-04 10:48
 **/
@Getter
@Setter
public class CurrentUser {

    private Integer userId;
    private List<String> roles;
    private List<String> permissions;

    public static CurrentUser getInstance(Integer userId,List<String> roles,List<String> permissions){
        CurrentUser currentUser = new CurrentUser();
        currentUser.userId = userId;
        currentUser.roles = roles;
        currentUser.permissions = permissions;
        return currentUser;
    }

    private CurrentUser(){}
}
