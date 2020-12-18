package com.likey.zhoubao.sys.models.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * @description: 登录传入信息
 * @author: likeyu
 * @create: 2020-12-01 10:35
 **/
@Getter
public class LoginQuery {

    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
}
