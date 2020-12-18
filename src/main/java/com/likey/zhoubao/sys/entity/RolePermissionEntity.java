package com.likey.zhoubao.sys.entity;

import com.likey.zhoubao.common.models.BaseEntity;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @description: 角色和权限对应实体类
 * @author: likeyu
 * @create: 2020-11-12 14:28
 **/
@EntityListeners(AuditingEntityListener.class)//使用@createBy等需要
@Data
@Entity
@Table(name = "role_permission")
public class RolePermissionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_permission_id")
    private Integer rolePermissionId;
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "permission_id")
    private Integer permissionId;

}
