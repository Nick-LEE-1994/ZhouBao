package com.likey.zhoubao.sys.entity;

import com.likey.zhoubao.common.models.BaseEntity;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @description: 用户和角色对应实体类
 * @author: likeyu
 * @create: 2020-11-12 14:28
 **/
@EntityListeners(AuditingEntityListener.class)//使用@createBy等需要
@Data
@Entity
@Table(name = "user_role")
public class UserRoleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private Integer userRoleId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "role_id")
    private Integer roleId;

}
