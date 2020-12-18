package com.likey.zhoubao.sys.models.vo;

import com.likey.zhoubao.sys.entity.PermissionEntity;
import com.likey.zhoubao.sys.entity.RoleEntity;
import com.likey.zhoubao.sys.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @description:
 * @author: likeyu
 * @create: 2020-11-10 15:26
 **/
@Getter
@Setter
public class UserVO {
    private String token;

    private Integer userId;
    private String username;
    private String name;
    private Integer sex;
    List<String> roles;
    List<String> permissions;

    public static UserVO getUserVO(UserEntity userEntity){
        UserVO userVO = new UserVO();
        userVO.setUserId(userEntity.getUserId());
        userVO.setUsername(userEntity.getUsername());
        userVO.setName(userEntity.getName());
        userVO.setSex(userEntity.getSex());
        return userVO;
    }
}
