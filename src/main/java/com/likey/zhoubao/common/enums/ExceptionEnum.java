package com.likey.zhoubao.common.enums;

/**
 * @description:
 * @author: likeyu
 * @create: 2020-11-18 14:32
 **/
public enum ExceptionEnum {

    /* 成功状态码 */
    SUCCESS(1,"成功"),
    REQUEST_PARAMS_ERROR(1001,"参数校验失败"),
    /* 2--- 用户相关错误码 */
    USER_NOT_EXIST(2001,"用户不存在"),
    USER_PASSWORD_WRONG(2002,"用户密码错误"),
    USER_UNAUTHORIZED(2003,"用户权限异常"),
    USER_USERNAME_EXIST(2004,"用户名已存在"),

    /* 3--- 角色相关错误码 */
    ROLE_EXIST(3001,"该角色已存在"),

    /* 4--- 权限相关错误码 */
    PERMISSION_EXIST(4001,"该权限已存在")
    ;


    private Integer code;
    private String message;

    ExceptionEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    public Integer getCode(){return this.code;}
    public String getMessage(){return this.message;}


}
