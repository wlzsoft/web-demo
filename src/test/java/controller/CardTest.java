package controller;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.service.LoreCradService;
import com.pmp.entity.CardEntity;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath*:spring-servlet.xml","classpath*:spring-mybatis.xml"})
public class CardTest {
	
	@Autowired
	private LoreCradService loreCradService;
	
	
	@Test
	public void savaCrad(){	
		
		CardEntity entity = new CardEntity();
		entity.setPointId(17);
	    entity.setCreateId(1);
		entity.setCreateTime(new Date());
		loreCradService.savaLoreCrad(entity);
	}
	
	@Test
	public void delCrad(){	
		String loreCardId ="11";
		loreCradService.delLoreCrad(Integer.parseInt(loreCardId));
	}
	
	
	@Test
	public void editCrad(){	
		CardEntity entity = new CardEntity();
		entity.setId(10);
	    entity.setUpdateId(1);
		entity.setUpdateTime(new Date());
		loreCradService.editLoreCrad(entity);
	}
	
	
	
	
}
