package controller;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.entity.LoreCardEntity;
import com.demo.service.LoreCradService;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath*:spring-servlet.xml","classpath*:spring-mybatis.xml"})
public class CardTest {
	
	@Autowired
	private LoreCradService loreCradService;
	
	
	@Test
	public void savaCrad(){	
		
		LoreCardEntity entity = new LoreCardEntity();
		entity.setLorePointId(17);
		entity.setLanguage(1);
		entity.setArea(1);
		entity.setQuestionType("radio ");
		entity.setDifficultyLevel(5);
		entity.setTitleText("下面问题那个是正确的");
		entity.setQuestionText("魅族MRO111的发布日期");
		  String images ="{\"http://ouq4b0pql.bkt.clouddn.com/my-java3.png\",\"http://ouq4b0pql.bkt.clouddn.com/my-java1.png\"}";
		entity.setQuestionImages(images);
	      String srt="[{text:'2014-05-05',right:1,imageUrl:{\"http://ouq4b0pql.bkt.clouddn.com/my-java3.png\",\"http://ouq4b0pql.bkt.clouddn.com/my-java1.png\"}},{text:'2014-05-02',right:0,imageUrl:{\"http://ouq4b0pql.bkt.clouddn.com/my-java3.png\",\"http://ouq4b0pql.bkt.clouddn.com/my-java1.png\"}},{text:'2014-05-03',right:0,imageUrl:{\"http://ouq4b0pql.bkt.clouddn.com/my-java3.png\",\"http://ouq4b0pql.bkt.clouddn.com/my-java1.png\"}}]";
	    entity.setAnswers(srt);
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
		LoreCardEntity entity = new LoreCardEntity();
		entity.setId(10);
		entity.setLanguage(2);
		entity.setArea(2);
		entity.setQuestionType("checkbox ");
		entity.setDifficultyLevel(8);
		entity.setTitleText("下面问题那个是正确的");
		entity.setQuestionText("魅族MRO111的发布日期");
	    entity.setUpdateId(1);
		entity.setUpdateTime(new Date());
		loreCradService.editLoreCrad(entity);
	}
	
	
	
	
}
