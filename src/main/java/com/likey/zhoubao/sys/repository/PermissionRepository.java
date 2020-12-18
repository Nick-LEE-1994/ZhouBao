package com.likey.zhoubao.sys.repository;

import com.likey.zhoubao.sys.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: likeyu
 * @create: 2020-11-06 10:19
 **/
@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity,Integer> {

    PermissionEntity findByPermissionName(String permissionName);
}
