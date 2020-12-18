package com.likey.zhoubao.sys.exception;

import com.likey.zhoubao.common.enums.ExceptionEnum;
import lombok.Getter;

/**
 * @description:
 * @author: likeyu
 * @create: 2020-11-18 15:03
 **/
@Getter
public class MyException extends RuntimeException{

    private ExceptionEnum exceptionEnum;

    public MyException(ExceptionEnum exceptionEnum){
        this.exceptionEnum = exceptionEnum;
    }
    public String getExceptionMsg(){
        return exceptionEnum.getMessage();
    }
}
