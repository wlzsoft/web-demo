package controller;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.service.LorePointService;
import com.pmp.entity.PointEntity;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath*:spring-servlet.xml","classpath*:spring-mybatis.xml"})
public class PointTest {
	
	@Autowired
	private LorePointService lorePointService;
	
	@Test
	public void savaPoint(){
		for(int i=1;i<=5;i++){
			PointEntity entity = new PointEntity();
			entity.setCreateId(1);
			entity.setCreateTime(new Date());
			entity.setPointName("魅族PRO_"+i);
			entity.setNumber(0);
			entity.setBookId(3);
			lorePointService.savaLorePoint(entity);
		}
	}
	
	@Test
	public void editPoint(){
		PointEntity entity = new PointEntity();
		entity.setId(7);
		entity.setUpdateId(1);
		entity.setUpdateTime(new Date());
		entity.setPointName("魅蓝 X");
		entity.setNumber(0);
		entity.setBookId(3);
		lorePointService.editLorePoint(entity);
	}

	@Test
	public void delPoint(){
		String id ="10";
		lorePointService.delLorePoint(Integer.parseInt(id));
	}
	
}
