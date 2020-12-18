package com.likey.zhoubao.config;

import com.likey.zhoubao.common.enums.ExceptionEnum;
import com.likey.zhoubao.sys.entity.UserEntity;
import com.likey.zhoubao.sys.exception.MyException;
import com.likey.zhoubao.sys.mapper.UserMapper;
import com.likey.zhoubao.sys.repository.UserRepository;
import com.likey.zhoubao.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: likeyu
 * @create: 2020-11-04 16:29
 **/
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /*
    *权限认证
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = JWTUtil.getUsername(principalCollection.toString());
        UserEntity user = userRepository.findByUsername(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(userMapper.selectRolesByUserId(user.getUserId()));
        simpleAuthorizationInfo.addStringPermissions(userMapper.selectPermissionsByUserId(user.getUserId()));
        return simpleAuthorizationInfo;
    }
    /*
    * 用户身份认证
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null)
            return null;
        String token = (String) authenticationToken.getCredentials();
        String username = JWTUtil.getUsername(token);
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null){
            throw new MyException(ExceptionEnum.USER_NOT_EXIST);
        }else {
//个人理解：帐号验证已经通过上面几步完成，SimpleAuthenticationInfo参数填写两个token是为了跳过shiro的帐号验证
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(token,token,getName());
            return simpleAuthenticationInfo;
        }
    }

}
