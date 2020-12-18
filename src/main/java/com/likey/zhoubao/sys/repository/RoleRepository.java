package com.likey.zhoubao.sys.repository;

import com.likey.zhoubao.sys.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: likeyu
 * @create: 2020-11-06 10:20
 **/
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
}
