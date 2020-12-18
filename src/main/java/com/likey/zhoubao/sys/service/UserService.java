package com.likey.zhoubao.sys.service;

import com.likey.zhoubao.common.models.ZBResponse;
import com.likey.zhoubao.sys.models.query.CreateRoleQuery;
import com.likey.zhoubao.sys.models.query.LoginQuery;
import com.likey.zhoubao.sys.models.query.RegistQuery;
import com.likey.zhoubao.sys.models.vo.UserVO;

/**
 * @description:
 * @author: likeyu
 * @create: 2020-10-30 14:45
 **/
public interface UserService {

    ZBResponse<UserVO> login(LoginQuery loginQuery);
    ZBResponse<UserVO> regist(RegistQuery registQuery);
    ZBResponse<Boolean> createRole(CreateRoleQuery createRoleQuery);
    ZBResponse<Boolean> createPermission(String permission);
}
