package com.demo.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.BookDao;
import com.demo.dao.PointDao;
import com.demo.dto.BookDto;
import com.demo.dto.BookProgressDto;
import com.demo.dto.IdEntity;
import com.demo.dto.PonitDto;
import com.demo.util.ToolUtil;
import com.pmp.entity.BookEntity;

@Service
public class ExcerciseBookService {
	
	@Autowired
	private SystemService systemService ;
	
	@Autowired
	private BookDao excerciseBookDao;
	
	@Autowired
	private PointDao lorePointDao;
	
	@Transactional
	public IdEntity bookSava(BookEntity entity){
		entity.setCreateId(systemService.getCurrentUser().getId());
		entity.setCreateTime(new Date());
		excerciseBookDao.bookSava(entity);
		IdEntity identity = new IdEntity();	
		identity.setId(entity.getId());

		//建立练习本和 练习本分类关系
		String bookClass = entity.getArea();
		String[] bookClassKeys = ToolUtil.toStringArry(bookClass);
		if(bookClassKeys.length>0){
			excerciseBookDao.addBook_class(entity.getId(), bookClassKeys);	
		}
		
		return identity;
	}
	
	/**
	 * 根据练习本ID 删除练习本
	 * @param bookId
	 * @return
	 */
	@Transactional
	public int delBook(String bookId){
		int count = excerciseBookDao.delBookById(bookId);
		lorePointDao.delPoinDetailtByBookId(Integer.parseInt(bookId));
		lorePointDao.delPointByBookId(Integer.parseInt(bookId));
		excerciseBookDao.delBook_class(Integer.parseInt(bookId));
		return count;
	}
	
	public int editBook(BookEntity entity){
		entity.setUpdateId(systemService.getCurrentUser().getId());
		entity.setUpdateTime(new Date());
		entity.setUpdateDetailId(systemService.getCurrentUser().getId());
		entity.setUpdateDetailTime(new Date());
		int count = excerciseBookDao.editBook(entity);
		//更新练习本对应的分类关系
		excerciseBookDao.delBook_class(entity.getId());
		//建立练习本和 练习本分类关系
		String bookClass = entity.getArea();
		String[] bookClassKeys = ToolUtil.toStringArry(bookClass);
		if(bookClassKeys.length>0){
			excerciseBookDao.addBook_class(entity.getId(), bookClassKeys);	
		}
		return count;
	}
	
	public BookEntity findBook(String id){
		BookEntity entity = excerciseBookDao.findBook(Integer.parseInt(id));
		return entity;
	}
	
	public BookDto findBookById(String id){
		BookDto entity = excerciseBookDao.findBookById(Integer.parseInt(id));
		return entity;
	}
	
	public List<BookDto> searchAllExcercise(Integer userId){
		List<BookDto> list = excerciseBookDao.searchAllExcercise(userId);
		return list;
	}
	
	public List<PonitDto> findExcerciseIdToPonit(Integer bookId){
		return excerciseBookDao.findExcerciseIdToPonit(bookId);
	}

	
	/**
	 * 获取所有共享类型练习本
	 * 注：不包含自己所共享出现的练习本
	 * @return
	 */
	public List<BookDto> getOpenBook(){
		Integer userId =  systemService.getCurrentUser().getId();
		List<BookDto> list = excerciseBookDao.getOpenBook(userId);
		return list;
	}
	
	public BookProgressDto bookProgress(Integer bookId){
		Integer userId =  systemService.getCurrentUser().getId();
		Integer count = excerciseBookDao.bookProgress(bookId,userId);
		Integer yes= excerciseBookDao.bookProgressYes(bookId,userId);
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
		return excerciseBookDao.findBookByPointId(pointId);
	}
	
    /**
     * 根据 cardId 查询 卡片所属练习本信息
     * @param cardId
     * @return
     */
    public BookEntity findBookByCardId(Integer cardId){
    	return excerciseBookDao.findBookByCardId(cardId);
	}
    
    /**
     * 如果创建者 更新了 练习下面的 知识点 或 卡片信息 练习本需要记录 更新时间
     * 
     * @param bookId
     */
    public void updateDetailBybookId(Integer bookId){
    	Integer userId =  systemService.getCurrentUser().getId();
    	excerciseBookDao.updateDetail(bookId, userId, new Date());
    }
    
    public void updateDetailBycardId(Integer cardId){
    	Integer userId =  systemService.getCurrentUser().getId();
    	BookEntity entity = findBookByCardId(cardId);
    	excerciseBookDao.updateDetail(entity.getId(), userId, new Date());
    }
    
    public void updateDetailByPointId(Integer pointId){
    	Integer userId =  systemService.getCurrentUser().getId();
    	BookEntity entity = findBookByPointId(pointId);
    	excerciseBookDao.updateDetail(entity.getId(), userId, new Date());
    }
    
    
    
 

}
