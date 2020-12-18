package com.likey.zhoubao.common.enums;

/**
 * @description:
 * @author: likeyu
 * @create: 2020-10-30 11:12
 **/
public enum DelFlag {

    ACTIVE(0),
    INACTIVE(1);

    private Integer value;

    DelFlag(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return this.value;
    }
}
