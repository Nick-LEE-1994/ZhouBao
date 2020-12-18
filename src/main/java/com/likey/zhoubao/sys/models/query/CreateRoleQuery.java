package com.likey.zhoubao.sys.models.query;

import java.util.List;

/**
 * @description: 创建角色时上传的参数
 * @author: likeyu
 * @create: 2020-12-08 13:43
 **/
public class CreateRoleQuery {

    private String roleName;
    private List<Integer> permissionIds;
}
