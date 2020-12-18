package com.likey.zhoubao;

import com.likey.zhoubao.sys.mapper.UserMapper;
import com.likey.zhoubao.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZhouBaoApplicationTests {

	@Autowired
	UserMapper userMapper;
	@Autowired
	RedisUtil redisUtil;

	@Test
	public void redisTest() {
//		redisUtil.putUserToken("token","userinfo");
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MDczMDY0NTksInVzZXJuYW1lIjoidXNlcjIifQ.MSWgYQLL7G_YIutgUMKNrFobBeDfcVTPEI5hrIc8BTw";

		System.out.println(redisUtil.getUserToken(token));
	}


	@Test
	public void userTest(){
		List<String> permissions = userMapper.selectPermissionsByUserId(1);
		for (String s : permissions)
			System.out.println(s);
	}

}
