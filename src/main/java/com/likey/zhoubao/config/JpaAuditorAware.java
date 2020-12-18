package com.likey.zhoubao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @description: jpa审计当前用户获取（@Configuration不太明白）
 * @author: likeyu
 * @create: 2020-11-02 15:13
 **/
@Configuration
public class JpaAuditorAware implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {
        Integer currentUserId = 1;
        return Optional.of(currentUserId);
    }
}
