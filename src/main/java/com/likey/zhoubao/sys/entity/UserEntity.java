package com.likey.zhoubao.sys.entity;

import com.likey.zhoubao.common.models.BaseEntity;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description: 用户实体类
 * @author: likeyu
 * @create: 2020-10-30 10:59
 **/
@EntityListeners(AuditingEntityListener.class)//使用@createBy等需要
@Data
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
//    @NotNull(message = "用户id不可为空")
    private Integer userId;

    private String username;
    private String password;
    private String name;
    private Integer sex;//0女1男

}
