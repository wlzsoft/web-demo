package controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.service.UserService;
import com.pmp.entity.UserEntity;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath*:spring-servlet.xml","classpath*:spring-mybatis.xml"})
public class UserTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void savaUser(){
		UserEntity userentity = new UserEntity();
		userentity.setUsername("wlz");
		userentity.setPassword("123456");
		userentity.setEmail("wang@163.com");
		userentity.setDegree(5);
		userService.savaUser(userentity);
	}
	
	
	

	
}
