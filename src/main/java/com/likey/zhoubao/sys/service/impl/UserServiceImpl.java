package com.likey.zhoubao.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.likey.zhoubao.common.enums.ExceptionEnum;
import com.likey.zhoubao.common.models.CurrentUser;
import com.likey.zhoubao.common.models.ZBResponse;
import com.likey.zhoubao.sys.entity.PermissionEntity;
import com.likey.zhoubao.sys.entity.UserEntity;
import com.likey.zhoubao.sys.exception.MyException;
import com.likey.zhoubao.sys.mapper.UserMapper;
import com.likey.zhoubao.sys.models.query.CreateRoleQuery;
import com.likey.zhoubao.sys.models.query.LoginQuery;
import com.likey.zhoubao.sys.models.query.RegistQuery;
import com.likey.zhoubao.sys.models.vo.UserVO;
import com.likey.zhoubao.sys.repository.PermissionRepository;
import com.likey.zhoubao.sys.repository.RoleRepository;
import com.likey.zhoubao.sys.repository.UserRepository;
import com.likey.zhoubao.sys.service.UserService;
import com.likey.zhoubao.util.JWTUtil;
import com.likey.zhoubao.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @description:
 * @author: likeyu
 * @create: 2020-10-30 14:45
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public ZBResponse<UserVO> login(LoginQuery loginQuery) {
        String username = loginQuery.getUsername();
        String password = loginQuery.getPassword();

        //验证用户名和密码
        UserEntity user = userRepository.findByUsername(username);
        if (user == null)
            throw new MyException(ExceptionEnum.USER_NOT_EXIST);
        if (!user.getPassword().equals(password))
            throw new MyException(ExceptionEnum.USER_PASSWORD_WRONG);
        //填充需要返回的数据
        Integer userId = user.getUserId();
        List<String> roles = userMapper.selectRolesByUserId(userId);
        List<String> permissions = userMapper.selectPermissionsByUserId(userId);

        UserVO userVO = new UserVO();
        userVO.setUserId(userId);
        userVO.setUsername(username);
        userVO.setName(user.getName());
        userVO.setSex(user.getSex());
        userVO.setRoles(roles);
        userVO.setPermissions(permissions);
        userVO.setToken(JWTUtil.sign(username,password));

        CurrentUser currentUser = CurrentUser.getInstance(userId,roles,permissions);
        //token存入redis
        redisUtil.putUserToken(userVO.getToken(), JSON.toJSONString(currentUser));

        return new ZBResponse<>(userVO);
    }

    @Override
    public ZBResponse<UserVO> regist(RegistQuery registQuery) {
        if (null != userRepository.findByUsername(registQuery.getUsername()))
            throw new MyException(ExceptionEnum.USER_USERNAME_EXIST);
        UserEntity user = new UserEntity();
        user.setUsername(registQuery.getUsername());
        user.setPassword(registQuery.getPassword());
        user.setSex(registQuery.getSex());
        user.setName(registQuery.getName());
        userRepository.save(user);
        UserVO userVO = UserVO.getUserVO(userRepository.findByUsername(registQuery.getUsername()));
        return new ZBResponse<>(userVO);
    }

    @Override
    public ZBResponse<Boolean> createRole(CreateRoleQuery createRoleQuery) {
        return null;
    }

    @Override
    public ZBResponse<Boolean> createPermission(String permission) {
        if (null != permissionRepository.findByPermissionName(permission))
            throw new MyException(ExceptionEnum.PERMISSION_EXIST);
        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setPermissionName(permission);
        permissionRepository.save(permissionEntity);
        return new ZBResponse<>(true);
    }

}
