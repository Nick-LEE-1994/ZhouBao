package com.likey.zhoubao.util;

import com.likey.zhoubao.common.models.CurrentUser;

/**
 * @description: 线程私有内存，为每次请求提供全局用户信息调用
 * @author: likeyu
 * @create: 2020-12-04 11:03
 **/
public class CurrentUserHolder {

    private static ThreadLocal<CurrentUser> threadLocal = new ThreadLocal<CurrentUser>();

    /**
     * 设置用户信息
     * @param user
     */
    public static void setUser(CurrentUser user){
        threadLocal.set(user);
    }
    /**
     * 获取用户信息
     * @return
     */
    public static CurrentUser getUser() {
        CurrentUser user = threadLocal.get();
        return user;
    }
    /**
     * 移除用户
     */
    public static void removeUser() {
        threadLocal.remove();
    }
}
