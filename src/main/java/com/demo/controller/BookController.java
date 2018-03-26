package com.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dao.ExcerciseDao;
import com.demo.dao.PointDao;
import com.demo.dto.BookDto;
import com.demo.dto.BookNumDto;
import com.demo.dto.BookProgressDto;
import com.demo.dto.IdEntity;
import com.demo.dto.PointExerciseDetailDto;
import com.demo.dto.PointNumDto;
import com.demo.dto.PonitSkilledDto;
import com.demo.dto.UserBookDto;
import com.demo.service.ExcerciseBookService;
import com.demo.service.ExcerciseService;
import com.demo.service.LorePointService;
import com.demo.service.ReviewService;
import com.demo.service.SystemService;
import com.demo.service.UserBookService;
import com.demo.service.UtilService;
import com.pmp.entity.BookEntity;
import com.pmp.entity.UserBookEntity;
import com.smartframe.dto.Result;
import com.smartframe.dto.ResultObject;


/**
 * 练习本控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private ExcerciseBookService bookService ;	
	
	
	@Autowired
	private ExcerciseService excerciseService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private SystemService systemService ;
	
	@Autowired
	private UserBookService userBookService;
	
	@Autowired
	private LorePointService lorePointService;
	
	@Autowired
	private PointDao lorePointDao;
	
	@Autowired
	private UtilService utilService;
	
	@Autowired
	private ExcerciseDao excerciseDao;
	
	
	/**
	 * 
	 * 新增保存练习本节点
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/addBook")
	public Result<?> savaExcercise(HttpServletRequest request ,HttpServletResponse response,BookEntity entity){
		
		if(null==entity.getBookName()||entity.getBookName().equals("")){
			return ResultObject.warnMessage("练习本名称不能为空");
		}
		
		if(null==entity.getLanguage()||entity.getLanguage().equals("")){
			return ResultObject.warnMessage("语言不能为空");
		}
		
		IdEntity identity = bookService.bookSava(entity);
		UserBookEntity userBookEntity = new UserBookEntity();
					userBookEntity.setBookId(identity.getId());
					userBookEntity.setUserId(systemService.getCurrentUser().getId());
		userBookService.addUserBook(userBookEntity);
		return ResultObject.successObject(identity,"保存成功");
	}
	
	/**
	 * 根据练习本ID删除 练习本节点
	 * @param request
	 * @param response
	 * @param excerciseId 练习本ID
	 * @return
	 */
	@RequestMapping("/delBook")
	public Result<?> delExcercise(HttpServletRequest request ,HttpServletResponse response,String bookId){
		if(null==bookId||bookId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		/**
		 * 加操作权限
		 * */
		BookEntity entity = bookService.findBook(bookId);
		if(null==entity){
			return ResultObject.warnMessage("无操作权限");	
		}
		Integer userId = systemService.getCurrentUser().getId();
		if(userId!=entity.getCreateId()){
			return ResultObject.warnMessage("无操作权限");
		}else{
			int count = bookService.delBook(bookId);
			if(count==0){
				return ResultObject.successMessage("无操作数据");
			}
			//同时删除用户练习本关联关系表数据
			userBookService.delUserBookBybooKId(Integer.parseInt(bookId));	
			return ResultObject.successMessage("删除成功");
		}
		
	}
	
	/**
	 * 更新练习本节点数据
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/editBook")
	public Result<?> editBook(HttpServletRequest request ,HttpServletResponse response,BookEntity entity){
		if(null==entity.getId()||entity.getId().equals("")){
			return ResultObject.warnMessage("主键ID不能为空");
		}
		
		if(null==entity.getBookName()||entity.getBookName().equals("")){
			return ResultObject.warnMessage("练习本名称不能为空");
		}
		
		/**
		 * 加操作权限
		 * */
		BookEntity bookEntity = bookService.findBook(entity.getId().toString());
		if(null==bookEntity){
			return ResultObject.warnMessage("无操作权限");	
		}
		Integer userId = systemService.getCurrentUser().getId();
		if(userId!=bookEntity.getCreateId()){
			return ResultObject.warnMessage("无操作权限");	
		}else{
			int count = bookService.editBook(entity);
			if(count==0){
				return ResultObject.successMessage("无操作数据");
			}
		  return ResultObject.successMessage("修改成功");		
		}
	}
	
	/**
	 * 根据练习本ID 查询练习本信息
	 * @param request
	 * @param response
	 * @param excerciseId
	 * @return
	 */
	@RequestMapping("/findBook")
	public Result<?> findBook(HttpServletRequest request ,HttpServletResponse response,String bookId){
		if(null==bookId||bookId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		
		/**
		 * 加操作权限
		 * */
		BookDto entity = bookService.findBookById(bookId);
		if(null==entity){
			return ResultObject.warnMessage("无操作权限");
		}else{
			if(entity.getSharedType()==0){
				Integer userId = systemService.getCurrentUser().getId();
	 			List<UserBookEntity>  list = userBookService.findUser_userId_bookId(userId, entity.getId());
	 			if(list.size()==0){
	 				return ResultObject.warnMessage("无操作权限");	
	 			}
			}
		}

		
		return ResultObject.successObject(entity,null);	
	}
	
	/**
	 * 根据用户ID 查询所有练习本信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/bookList")
	public Result<List<BookDto>> bookList(HttpServletRequest request ,HttpServletResponse response){
		Integer userId =systemService.getCurrentUser().getId();
		List<BookDto> entityList = bookService.searchAllExcercise(userId);
		return ResultObject.successObject(entityList,null);
	}
	
	
	/**
	 * 根据用户ID 查询用户练习练习本的信息
	 * @param request
	 * @param response
	 * 
	 * 【该方法需要优化】目前是根据 searchAllExcercise 查询用户所有练习本信息，可以直接查询 userBook表信息 速度更快
	 * 
	 * @return
	 */
	@RequestMapping("/bookReviewInfo")
	public Result<?> bookReviewInfo(HttpServletRequest request ,HttpServletResponse response){
		Integer userId =systemService.getCurrentUser().getId();
		List<BookDto> entityList = bookService.searchAllExcercise(userId);
		if(entityList.size()>0){
			String[] bookIds = new String[entityList.size()];
			for(int i=0;i<entityList.size();i++){
				BookDto dto =entityList.get(i); 
				bookIds[i]=dto.getId().toString();
			}
			List<PointNumDto> dtoList = excerciseService.getPointNum(userId,bookIds);
			List<BookNumDto> bookDtoList = new ArrayList<>();
			for(PointNumDto dto:dtoList){
				BookNumDto bookNum =new BookNumDto();
				bookNum.setBookId(dto.getBookId());
				
				//1、查询练习本下所有知识点
				List<PonitSkilledDto> pointList1 =lorePointDao.findBookIdToPonit_card(dto.getBookId(),userId);
				bookNum.setPointNum(pointList1.size());
				//2、查询练习本下所有被练习的知识点
				List<PonitSkilledDto> pointList2 =lorePointDao.findBookIdToPonit_ex(dto.getBookId(), userId);
				bookNum.setPointNumY(pointList2.size());
				//3、查询今日练习新的题目 数量
				List<PointExerciseDetailDto> list_new = reviewService.getDailyGoals(new Date(), dto.getBookId());
				bookNum.setCompleteNum(list_new.size());
				//4、查询练习本设置的
				List<UserBookEntity> userBookList = userBookService.findUser_userId_bookId(userId, dto.getBookId());
				if(userBookList.size()>0){
					Integer dailyGoals = userBookList.get(0).getDailyGoal();
					bookNum.setDailyGoal(dailyGoals);
				}
				
				//状态  0：新增   1：上次答错    2：巩固   3：强化 
				if(dto.getExErrorNum()>0){
					bookNum.setExNum(dto.getExErrorNum());//错题	
					bookNum.setState(1); // 1：上次答错       
				}else if(dto.getExStrengthenNum()>0){
					bookNum.setExNum(dto.getExStrengthenNum());//巩固
					bookNum.setState(2); // 2：巩固      
				}else if(dto.getExNewNum()>0){
					if(bookNum.getCompleteNum()<bookNum.getDailyGoal()){
						Integer exnum = bookNum.getDailyGoal()-bookNum.getCompleteNum();//剩余需要练习的数量
						bookNum.setExNum(exnum);//练新
						bookNum.setState(0); //0：新增   	
					}else{
						bookNum.setExNum(dto.getExIntensifyNum());//强化
						bookNum.setState(3 ); //3：强化   
					}
				}else{
					bookNum.setExNum(dto.getExIntensifyNum());//强化
					bookNum.setState(3 ); // 3：强化   
				}
				
				bookNum.setContinueNum(0);
				bookDtoList.add(bookNum);
			}
			return ResultObject.successObject(bookDtoList,null) ;
		}
		return ResultObject.warnMessage("没有练习本信息");
	}
	
	

    
	/**
	 * 根据练习本ID ， 查询用户练习本练习进度
	 * @param request
	 * @param response
	 * @param excerciseId
	 * @return
	 */
	@RequestMapping("/bookProgress")
	public Result<?> bookProgress(HttpServletRequest request ,HttpServletResponse response,String bookId){
		if(null==bookId||bookId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		
		/**
		 * 加操作权限
		 * */
		BookEntity entity = bookService.findBook(bookId);
		if(null==entity){
			return ResultObject.warnMessage("无操作权限");	
		}
		if(entity.getSharedType()==0){
			Integer userId = systemService.getCurrentUser().getId();
 			List<UserBookEntity>  list = userBookService.findUser_userId_bookId(userId, entity.getId());
 			if(list.size()==0){
 				return ResultObject.warnMessage("无操作权限");	
 			}
 			/*	if(userId!=entity.getCreateId()){
				return ResultObject.warnMessage("无操作权限");	
			}*/
			
		}
		
		
		BookProgressDto bPentity = bookService.bookProgress(Integer.parseInt(bookId));
		return ResultObject.successObject(bPentity,null); 
	}
	
	/**
	 * 用户订阅共享练习本接口
	 * @param request
	 * @param response
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/subscribe")
	public Result<?> addUserBook(HttpServletRequest request ,HttpServletResponse response,String bookId){
		if(null==bookId||bookId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		
		
		/**
		 * 加操作权限
		 * */
		BookEntity entity = bookService.findBook(bookId);
		if(null==entity){
			return ResultObject.warnMessage("无操作权限");	
		}
		if(entity.getSharedType()==0){
			Integer userId = systemService.getCurrentUser().getId();
 			List<UserBookEntity>  list = userBookService.findUser_userId_bookId(userId, entity.getId());
 			if(list.size()==0){
 				return ResultObject.warnMessage("无操作权限");	
 			}
 			/*	if(userId!=entity.getCreateId()){
				return ResultObject.warnMessage("无操作权限");	
			}*/
			
		}
		
		List<UserBookEntity> list = userBookService.findUser_userId_bookId(systemService.getCurrentUser().getId(), Integer.parseInt(bookId));
		if(list.size()>0){
			return ResultObject.warnMessage("练习本已经订阅");
		}else{
			UserBookEntity userBookEntity = new UserBookEntity();
			  userBookEntity.setBookId(Integer.parseInt(bookId));
			  userBookEntity.setUserId(systemService.getCurrentUser().getId());
	    	userBookService.addUserBook(userBookEntity);
		    lorePointService.pushPoint(Integer.parseInt(bookId),systemService.getCurrentUser().getId());//根据bookId推送到订阅的用户
		   return ResultObject.successMessage("订阅成功");
		}
	}
	
	/**
	 * 获取所有共享类型练习本
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getShareBook")
	public Result<?> getShareBook(HttpServletRequest request ,HttpServletResponse response){
		List<BookDto> entityList = bookService.getOpenBook();
		return ResultObject.successObject(entityList,null);
	}
	
	
	/**
	 * 取消对练习本的订阅
	 * @param request
	 * @param response
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/unsubscribe")
	public Result<?>  unsubscribe(HttpServletRequest request ,HttpServletResponse response ,String bookId){
		if(null==bookId||bookId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		Integer userId = systemService.getCurrentUser().getId();
		
		/**
		 * 加操作权限
		 * */
		BookEntity entity = bookService.findBook(bookId);
		if(null==entity){
			return ResultObject.warnMessage("无操作权限");	
		}
		if(entity.getCreateId()==userId){
			return ResultObject.warnMessage("原创不能取消");	
		}
		
		int count = userBookService.delUserBook(userId,Integer.parseInt(bookId));
		if(count==0){
			return ResultObject.successMessage("该用户没有订阅");
		}
		return ResultObject.successMessage("取消订阅成功");
	}
	
	/**
	 * 修改用户练习本 目标数量、练习本是否隐藏
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/updateUserBook")
	public Result<?> updateUserBook(HttpServletRequest request ,HttpServletResponse response ,UserBookEntity entity){
		if(null==entity.getBookId()||entity.getBookId().equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		if(null==entity.getDailyGoal()||entity.getDailyGoal().equals("")){
			entity.setDailyGoal(5);
		}
		
		if(null==entity.getHidden()||entity.getHidden().equals("")){
			entity.setHidden(0);//默认不隐藏
		}
		
		userBookService.updateUserBook(entity);
		
		return ResultObject.successMessage("修改成功");
	}
	
	
	/**
	 * 获取用户练习本 目标数量、练习本是否隐藏
	 * @param request
	 * @param response
	 * @param entity
	 * @return
	 */
	@RequestMapping("/getUserBook")
	public Result<?> getUserBook(HttpServletRequest request ,HttpServletResponse response ,String bookId){
		if(null==bookId||bookId.equals("")){
			return ResultObject.warnMessage("参数不能为空");
		}
		
		/**
		 * 加操作权限
		 * */
		BookEntity entity = bookService.findBook(bookId);
		if(null==entity){
			return ResultObject.warnMessage("无操作权限");	
		}
		
		Integer userId = systemService.getCurrentUser().getId();
		UserBookDto dto =  userBookService.findUserBook(userId , Integer.parseInt(bookId));
		if(null!=dto){
			//1、查询练习本下所有知识点
			List<PonitSkilledDto> pointList1 =lorePointDao.findBookIdToPonit_card(dto.getBookId(),userId);
			Integer pointNum= 0;
			 if(pointList1.size()>0){
				 pointNum= pointList1.size();
			 }
			//2、查询练习本下所有被练习的知识点
			List<PonitSkilledDto> pointList2 =lorePointDao.findBookIdToPonit_ex(dto.getBookId(), userId);
			Integer pointNumY =0;
			 if(pointList2.size()>0){
				 pointNumY= pointList2.size();
			 }
			 Integer pointNumRemain =pointNum- pointNumY;
			 dto.setPointNumRemain(pointNumRemain);
			return ResultObject.successObject(dto, null);
		}else{
			return ResultObject.successMessage("没有数据");
		}
		
	}
	
	
	
	
}
