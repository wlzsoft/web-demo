package controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.service.ChapterService;
import com.pmp.entity.ChapterEntity;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath*:spring-servlet.xml","classpath*:spring-mybatis.xml"})
public class ChapterTest {

	@Autowired
	private ChapterService chapterService;
	
	@Test
	public void addChapter(){
		ChapterEntity entity = new ChapterEntity();
		for(int i=22;i<30;i++){
			entity.setBookId(i);
			chapterService.addChapter(entity);
		}
	}
	
	
}
