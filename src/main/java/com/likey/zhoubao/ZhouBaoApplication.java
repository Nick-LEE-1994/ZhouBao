package com.likey.zhoubao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing//@createBy等需要
@SpringBootApplication
@MapperScan("com.likey.zhoubao.sys.mapper")
public class ZhouBaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhouBaoApplication.class, args);
	}

}
