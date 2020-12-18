package com.likey.zhoubao.sys.entity;

import com.likey.zhoubao.common.models.BaseEntity;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @description:
 * @author: likeyu
 * @create: 2020-11-05 10:09
 **/
@EntityListeners(AuditingEntityListener.class)//使用@createBy等需要
@Data
@Entity
@Table(name = "permission")
public class PermissionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private int permissionId;
    @Column(name = "permission_name")
    private String permissionName;

}
