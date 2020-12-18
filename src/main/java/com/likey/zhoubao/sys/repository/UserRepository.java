package com.likey.zhoubao.sys.repository;

import com.likey.zhoubao.sys.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: likeyu
 * @create: 2020-10-30 14:09
 **/
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    UserEntity findByUsername(String username);
}
