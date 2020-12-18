package com.likey.zhoubao.common.models;

import com.likey.zhoubao.common.enums.ExceptionEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description: 统一返回前端格式
 * @author: likeyu
 * @create: 2020-11-18 13:56
 **/
@Getter
@Setter
public class ZBResponse<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public ZBResponse(){}
    public ZBResponse(T t){
        setCode(1);
        setMessage("SUCCESS_CODE");
        this.data = t;
    }
    public ZBResponse(ExceptionEnum exceptionEnum, T data){
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
        this.data = data;
    }
}
