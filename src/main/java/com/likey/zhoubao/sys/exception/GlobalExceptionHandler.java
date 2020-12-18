package com.likey.zhoubao.sys.exception;

import com.likey.zhoubao.common.enums.ExceptionEnum;
import com.likey.zhoubao.common.models.ZBResponse;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description:
 * @author: likeyu
 * @create: 2020-11-18 15:06
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyException.class)
    public ZBResponse myExceptionHandler(MyException e){
        ZBResponse response = new ZBResponse(e.getExceptionEnum(),"接口异常");
        return response;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ZBResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        ZBResponse response = new ZBResponse(ExceptionEnum.REQUEST_PARAMS_ERROR,objectError.getDefaultMessage());
        return response;
    }
    @ExceptionHandler(ShiroException.class)
    public ZBResponse shiroExceptionHandler(ShiroException e){
        ZBResponse response = null;
        if (e instanceof IncorrectCredentialsException)
            response = new ZBResponse(ExceptionEnum.USER_PASSWORD_WRONG,e.getMessage());
        if (e instanceof AuthorizationException)
            response = new ZBResponse(ExceptionEnum.USER_UNAUTHORIZED,e.getMessage());
        return response;
    }
}
