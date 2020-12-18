package com.likey.zhoubao.sys.mapper;

import com.likey.zhoubao.sys.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: likeyu
 * @create: 2020-11-12 15:35
 **/
public interface UserMapper {
    List<String> selectRolesByUserId(@Param("userId") Integer userId);
    List<String> selectPermissionsByUserId(@Param("userId") Integer userId);
}
