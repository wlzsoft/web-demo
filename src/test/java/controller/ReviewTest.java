package controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.service.ReviewService;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath*:spring-servlet.xml","classpath*:spring-mybatis.xml"})
public class ReviewTest {
	
	@Autowired
	private ReviewService reviewService;
	
	@Test
	public void savaReview(){
		
		String lorePointId="17";
		String cradId="10";
		Integer right=1 ;
		reviewService.reviewCrad(lorePointId, cradId, right);
	}
	
	
	@Test
	public void excercise(){
		
		String lorePointId="17";
		String cradId="10";
		Integer right=1 ;
		reviewService.reviewCrad(lorePointId, cradId, right);
	}

	
	
}
