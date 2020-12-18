package com.likey.zhoubao.sys.controller;

import com.likey.zhoubao.common.models.ZBResponse;
import com.likey.zhoubao.sys.models.query.CreateRoleQuery;
import com.likey.zhoubao.sys.models.query.LoginQuery;
import com.likey.zhoubao.sys.models.query.RegistQuery;
import com.likey.zhoubao.sys.models.vo.UserVO;
import com.likey.zhoubao.sys.repository.UserRepository;
import com.likey.zhoubao.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: likeyu
 * @create: 2020-10-30 14:13
 **/
@Api(tags = "用户相关api")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserService userService;

    @ApiOperation(value = "测试api")
    @RequiresPermissions("test")
    @GetMapping("/test")
    public String testCon(){
        System.out.println("test");
        return "ok";
    }

    @ApiOperation(value = "登录api")
    @PostMapping("/login")
    public ZBResponse<UserVO> login(@RequestBody LoginQuery loginQuery){
        return userService.login(loginQuery);
    }

    @ApiOperation(value = "注册api")
    @PostMapping("/register")
    public ZBResponse<UserVO> register(@RequestBody RegistQuery registQuery){
        return userService.regist(registQuery);
    }

    @PostMapping("/createRole")
    public ZBResponse<Boolean> createRole(@RequestBody CreateRoleQuery createRoleQuery){
        return userService.createRole(createRoleQuery);
    }

    @GetMapping("/createPermission/{permission}")
    public ZBResponse<Boolean> createPermission(@PathVariable("permission") String permission){
        return userService.createPermission(permission);
    }

}
