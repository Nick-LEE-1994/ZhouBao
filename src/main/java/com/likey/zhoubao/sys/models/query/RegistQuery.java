package com.likey.zhoubao.sys.models.query;

import lombok.Getter;

/**
 * @description: 注册信息传入
 * @author: likeyu
 * @create: 2020-12-07 17:36
 **/
@Getter
public class RegistQuery {
    private String username;
    private String password;
    private String name;
    private Integer sex;
}
