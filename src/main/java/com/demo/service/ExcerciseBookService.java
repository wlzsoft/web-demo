package com.demo.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.BookDao;
import com.demo.dao.BookPriceDao;
import com.demo.dao.PointDao;
import com.demo.dto.BookDto;
import com.demo.dto.BookProgressDto;
import com.demo.dto.IdEntity;
import com.demo.dto.PonitDto;
import com.demo.dto.UserBookInfoDto;
import com.demo.model.BookModel;
import com.demo.util.ToolUtil;
import com.pmp.entity.BookEntity;
import com.pmp.entity.PointEntity;
import com.pmp.entity.PointExerciseDetailEntity;
import com.pmp.entity.UserBookEntity;

@Service
public class ExcerciseBookService {
	
	@Autowired
	private SystemService systemService ;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private PointDao lorePointDao;
	
	@Autowired
	private BookPriceDao bookPriceDao;
	
	@Autowired
	private UserBookService userBookService;
	
	@Transactional
	public IdEntity bookSava(BookModel model){
		model.setCreateId(systemService.getCurrentUser().getId());
		model.setCreateTime(new Date());
		bookDao.bookSava(model);
		IdEntity identity = new IdEntity();	
		identity.setId(model.getId());

		//建立练习本和 练习本分类关系
		String bookClass = model.getArea();
		String[] bookClassKeys = ToolUtil.toStringArry(bookClass);
		if(bookClassKeys.length>0){
			bookDao.addBook_class(model.getId(), bookClassKeys);	
		}
		//建立练习本价格表
/*		BookPriceEntity entityPrice = new BookPriceEntity();
		entityPrice.setBookId(model.getId());
		entityPrice.setPrice(model.price);
		bookPriceDao.addBookPrice(entityPrice);*/
		return identity;
	}
	
	/**
	 * 根据练习本ID 删除练习本
	 * @param bookId
	 * @return
	 */
	@Transactional
	public int delBook(String bookId){
		int count = bookDao.delBookById(bookId);
		lorePointDao.delPoinDetailtByBookId(Integer.parseInt(bookId));
		lorePointDao.delPointByBookId(Integer.parseInt(bookId));
		bookDao.delBook_class(Integer.parseInt(bookId));
		return count;
	}
	
	public int editBook(BookEntity entity){
		entity.setUpdateId(systemService.getCurrentUser().getId());
		entity.setUpdateTime(new Date());
		entity.setUpdateDetailId(systemService.getCurrentUser().getId());
		entity.setUpdateDetailTime(new Date());
		int count = bookDao.editBook(entity);
		//更新练习本对应的分类关系
		bookDao.delBook_class(entity.getId());
		//建立练习本和 练习本分类关系
		String bookClass = entity.getArea();
		String[] bookClassKeys = ToolUtil.toStringArry(bookClass);
		if(bookClassKeys.length>0){
			bookDao.addBook_class(entity.getId(), bookClassKeys);	
		}
		return count;
	}
	
	public BookEntity findBook(String id){
		BookEntity entity = bookDao.findBook(Integer.parseInt(id));
		return entity;
	}
	
	public BookDto findBookById(String id){
		BookDto entity = bookDao.findBookById(Integer.parseInt(id));
		return entity;
	}
	
	public List<BookDto> searchAllExcercise(Integer userId){
		List<BookDto> list = bookDao.searchAllExcercise(userId);
		return list;
	}
	
	public List<PonitDto> findExcerciseIdToPonit(Integer bookId){
		return bookDao.findExcerciseIdToPonit(bookId);
	}

	
	/**
	 * 根据用户ID ，bookId  查询出 用户最近需要练习的知识点时间
	 * @param userId
	 * @return
	 */
	public PointExerciseDetailEntity findUserExBook(Integer userId,Integer bookId){
		return bookDao.findUserExBook(userId,bookId);
	}
	
	/**
	 * 获取所有共享类型练习本
	 * 注：不包含自己所共享出现的练习本
	 * @return
	 */
	public List<BookDto> getOpenBook(){
		Integer userId =  systemService.getCurrentUser().getId();
		List<BookDto> list = bookDao.getOpenBook(userId);
		return list;
	}
	
	public BookProgressDto bookProgress(Integer bookId){
		Integer userId =  systemService.getCurrentUser().getId();
		Integer count = bookDao.bookProgress(bookId,userId);
		Integer yes= bookDao.bookProgressYes(bookId,userId);
		DecimalFormat df=new DecimalFormat("0.00");
		double progress =Double.valueOf(df.format((float)yes/count));
		 BookProgressDto dto = new BookProgressDto();
		 dto.setBookId(bookId);
		 dto.setProgress(progress);
		return dto;
	}
	
	/**
	 * 根据 poindId 查询 知识点所属练习本信息
	 * @param pointId
	 * @return
	 */
	public BookEntity findBookByPointId(Integer pointId){
		return bookDao.findBookByPointId(pointId);
	}
	
    /**
     * 根据 cardId 查询 卡片所属练习本信息
     * @param cardId
     * @return
     */
    public BookEntity findBookByCardId(Integer cardId){
    	return bookDao.findBookByCardId(cardId);
	}
    
    /**
     * 如果创建者 更新了 练习下面的 知识点 或 卡片信息 练习本需要记录 更新时间
     * 
     * @param bookId
     */
    public void updateDetailBybookId(Integer bookId){
    	Integer userId =  systemService.getCurrentUser().getId();
    	bookDao.updateDetail(bookId, userId, new Date());
    }
    
    public void updateDetailBycardId(Integer cardId){
    	Integer userId =  systemService.getCurrentUser().getId();
    	BookEntity entity = findBookByCardId(cardId);
    	bookDao.updateDetail(entity.getId(), userId, new Date());
    }
    
    public void updateDetailByPointId(Integer pointId){
    	Integer userId =  systemService.getCurrentUser().getId();
    	BookEntity entity = findBookByPointId(pointId);
    	bookDao.updateDetail(entity.getId(), userId, new Date());
    }
    
    /**
     * 根据用户获取 练习本的总数  ，知识点的总数
     * @param userId
     * @return
     */
    public UserBookInfoDto getBookExNum(Integer userId){
    	//查询用户所订阅的所有练习本
    	List<UserBookEntity> userBookList = userBookService.findUserBookList(userId);
    	//产线用户所订阅的所有练习本下所有知识点
    	List<PointEntity> pointList = userBookService.findUserPointList(userId);
    	UserBookInfoDto dto = new UserBookInfoDto();
    	    dto.setBookNum(userBookList.size());
    	    dto.setPointNum(pointList.size());
    	    dto.setUserId(userId);
    	 return dto;
    }
    
 

}
