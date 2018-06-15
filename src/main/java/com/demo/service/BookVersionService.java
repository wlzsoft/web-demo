package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.BookVersionDao;
import com.pmp.entity.BookBranchEntity;
import com.pmp.entity.BookEntity;
import com.pmp.entity.CardBranchEntity;
import com.pmp.entity.CardEntity;
import com.pmp.entity.ChapterBranchEntity;
import com.pmp.entity.ChapterEntity;
import com.pmp.entity.PointBranchEntity;
import com.pmp.entity.PointEntity;
import com.smartframe.rediscache.dao.RedisStringDao;

@Service
public class BookVersionService {
	
	@Autowired
	public BookVersionDao bookVersionDao ;
	
	@Autowired
    private RedisStringDao redisStringDao;
	
	/**
	 * 提交发布版本
	 * @param bookId
	 */
	@Transactional
	public void commitBook(Integer bookId){
/*		    // 线程个数
	        final int SIZE = 4; //1:练习本 2：章节 3：知识点  4:卡片
	        // 交给单个线程处理，成功了就加1
	        final AtomicInteger successCounter = new AtomicInteger();*/
	        //查询练习本信息
	        BookEntity bookEntity = getBookById(bookId);
	        Integer version = bookEntity.getVersion()+1;//获取当前版本号，并升级1 个版本
	        //查询章节信息
	        List<ChapterEntity> chapterList = getChapterByBookId(bookId); 
	        //查询知识点信息
	        List<PointEntity> pointList =  getPointByBookId(bookId);
	        //查询卡片信息
	        List<CardEntity> cardtList = getCardByBookId(bookId);
	        /*
	         * 进行版本发布插入branch表
	         * */
	        insertBook(bookEntity,version);
	        inserChapter(chapterList,version);		
	        inserPoint(pointList,version);
	        inserCard(cardtList,version);
	 }
	
	/**审核发布版本
	 * @param bookId
	 * @param status
	 */
	public void auditorBook(Integer bookId,Integer status){
		bookVersionDao.updateBookBranch(bookId, status);
		if(status==3){//如果状态为3 更新开发版的当前版本号
			BookBranchEntity entity = bookVersionDao.findBookVersion(bookId);
			bookVersionDao.updateBook(bookId, entity.getVersion());
			//缓存练习本版本号
			redisStringDao.add("bookver_"+bookId, entity.getVersion());
		}
	}
	
	 
	 public BookEntity getBookById(Integer bookId){
		 return bookVersionDao.getBookById(bookId);
	 }
	 
	 public List<ChapterEntity> getChapterByBookId(Integer bookId){
		 return bookVersionDao.getChapterByBookId(bookId);
	 }
	 
	 public List<PointEntity> getPointByBookId(Integer bookId){
		 	return bookVersionDao.getPointByBookId(bookId);
	 }
	 
	 public List<CardEntity> getCardByBookId(Integer bookId){
		 return bookVersionDao.getCardByBookId(bookId);
	 }
	 
	 /**
	  * 发布练习本信息
	 * @param entity
	 * @param version
	 */
	public void insertBook(BookEntity entity,Integer version){
		 BookBranchEntity bookBranch = new BookBranchEntity();
		 	bookBranch.setBookId(entity.getId());
		 	bookBranch.setBookName(entity.getBookName());
		 	bookBranch.setLanguage(entity.getLanguage());
		 	bookBranch.setArea(entity.getArea());
		 	bookBranch.setSharedType(entity.getSharedType());
		 	bookBranch.setRemark(entity.getRemark());
		 	bookBranch.setVersion(version);
		 	bookBranch.setStatus(1);//1:待审核  2:审核中  3:审核通过 4: 审核不通过
		 	bookVersionDao.insertBookBranch(bookBranch);
	 }
	 
	 /**
	  * 发布章节信息
	 * @param chapterList
	 * @param version
	 */
	public void inserChapter(List<ChapterEntity> chapterList,Integer version){
		 if(chapterList.size()>0){
			 List<ChapterBranchEntity> list = new ArrayList<>();
			 for(ChapterEntity entity:chapterList){
				 ChapterBranchEntity chapterBranch = new ChapterBranchEntity();
				 chapterBranch.setBookId(entity.getBookId());
				 chapterBranch.setChapterId(entity.getId());
				 chapterBranch.setParentId(entity.getParentId());
				 chapterBranch.setName(entity.getName());
				 chapterBranch.setLevel(entity.getLevel());
				 chapterBranch.setSort(entity.getSort());
				 chapterBranch.setIsOpen(entity.getIsOpen());
				 chapterBranch.setVersion(version);
				 list.add(chapterBranch);
			 }
			 bookVersionDao.inserChapterBranch(list);
		 }
	 }
	 
	 /**
	  * 发布知识点信息
	 * @param pointList
	 * @param version
	 */
	public void inserPoint(List<PointEntity> pointList,Integer version){
		 if(pointList.size()>0){
			 List<PointBranchEntity> list = new ArrayList<>(); 
			 for(PointEntity entity:pointList){
					 PointBranchEntity pointBranch = new PointBranchEntity();
					 pointBranch.setBookId(entity.getBookId());
					 pointBranch.setChapterId(entity.getChapterId());
					 pointBranch.setPointId(entity.getId());
					 pointBranch.setPointName(entity.getPointName());
					 pointBranch.setNumber(entity.getNumber());
					 pointBranch.setSort(entity.getSort());
					 pointBranch.setChapterSort(entity.getChapterSort());
					 pointBranch.setVersion(version);
				 list.add(pointBranch);
			 }
			 bookVersionDao.inserPointBranch(list);
		 }
	 }
	 
	 /**
	  * 发布卡片信息
	 * @param cardList
	 * @param version
	 */
	public void inserCard(List<CardEntity> cardList,Integer version){
		 if(cardList.size()>0){
			 List<CardBranchEntity> list = new ArrayList<>(); 
			 for(CardEntity entity:cardList){
				 CardBranchEntity cardBranch = new CardBranchEntity();
					 cardBranch.setBookId(entity.getBookId());
					 cardBranch.setPointId(entity.getPointId());
					 cardBranch.setCardId(entity.getId());
					 cardBranch.setCardData(entity.getCardData());
					 cardBranch.setVersion(version);
				 list.add(cardBranch);
			 }
			 bookVersionDao.inserCardBranch(list);
		 }
	 }
	
	public BookBranchEntity findBookBranch(Integer bookId){
		return bookVersionDao.findBookBranch(bookId);
	}
	
}
