package com.likey.zhoubao.sys.entity;

import com.likey.zhoubao.common.models.BaseEntity;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/**
 * @description:
 * @author: likeyu
 * @create: 2020-11-05 10:09
 **/
@EntityListeners(AuditingEntityListener.class)//使用@createBy等需要
@Data
@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "role_name")
    private String roleName;

}
