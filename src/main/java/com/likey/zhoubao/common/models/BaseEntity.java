package com.likey.zhoubao.common.models;

import com.likey.zhoubao.common.enums.DelFlag;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @description: 基础实体类
 * @author: likeyu
 * @create: 2020-10-30 11:02
 **/

@MappedSuperclass
@Getter
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "create_time")
    public Date createTime;
    @LastModifiedDate
    @Column(name = "update_time")
    public Date updateTime;
    @CreatedBy
    @Column(name = "create_user")
    public Integer createUser;
    @LastModifiedBy
    @Column(name = "update_user")
    public Integer updateUser;
    @Column(name = "del_flag")
    public Integer delFlag = DelFlag.ACTIVE.getValue();

    public void able(){
        this.delFlag = DelFlag.ACTIVE.getValue();
    }
    public void disable(){
        this.delFlag = DelFlag.INACTIVE.getValue();
    }
}
