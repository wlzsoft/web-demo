package controller;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.entity.ExcerciseBookEntity;
import com.demo.service.ExcerciseBookService;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath*:spring-servlet.xml","classpath*:spring-mybatis.xml"})
public class BookTest {

	@Autowired
	private ExcerciseBookService excerciseService ;	
	
	@Test
	public void savaBook(){
		for(int i=10;i>0;i--){
			ExcerciseBookEntity entity = new ExcerciseBookEntity();
			entity.setLanguage("en");
			entity.setCreateId(1);
			entity.setCreateTime(new Date());
			entity.setBookName("魅族科技_"+i);
			entity.setSharedType(0);
			excerciseService.excerciseSava(entity);	
		}
	}
	
	@Test
	public void delBook(){
		String bookId = "1";
		excerciseService.delExcercise(bookId);
	}
	
	@Test
	public void editbook(){
		ExcerciseBookEntity entity = new ExcerciseBookEntity();
		entity.setId(22);
		entity.setBookName("魅族科技_22");
		entity.setArea("{'数学','奥数'}");
		entity.setSharedType(0);
		entity.setUpdateId(2);
		entity.setUpdateTime(new Date());
		excerciseService.editExcercise(entity);
	}
	
	
	@Test
	public void findbook(){
		String excerciseId = "22";
		ExcerciseBookEntity entity = excerciseService.findExcerciseId(excerciseId);
		System.out.println(entity.getId()+"_"+entity.getBookName());
	}
	
	@Test
	public void booklist(){
		Integer userId=1;
		List<ExcerciseBookEntity> entityList = excerciseService.searchAllExcercise(userId);
		System.out.println(entityList.size());
	}
	
}
