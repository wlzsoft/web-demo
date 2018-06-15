package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pmp.entity.BookBranchEntity;
import com.pmp.entity.BookEntity;
import com.pmp.entity.CardBranchEntity;
import com.pmp.entity.CardEntity;
import com.pmp.entity.ChapterBranchEntity;
import com.pmp.entity.ChapterEntity;
import com.pmp.entity.PointBranchEntity;
import com.pmp.entity.PointEntity;

@Repository
public interface BookVersionDao {
	
	 public BookEntity getBookById(@Param("bookId")Integer bookId);
	 
	 public List<ChapterEntity> getChapterByBookId(@Param("bookId")Integer bookId);
	 
	 public List<PointEntity> getPointByBookId(@Param("bookId")Integer bookId);
	 
	 public List<CardEntity> getCardByBookId(@Param("bookId")Integer bookId);
	 
	 public void insertBookBranch(BookBranchEntity entity);
	 
	 public void inserChapterBranch(List<ChapterBranchEntity> chapterList);
	 
	 public void inserPointBranch(List<PointBranchEntity> pointList);
	 
	 public void inserCardBranch(List<CardBranchEntity> cardList);
	 
	 /**
	 * 更新练习本状态
	 */
	public void updateBookBranch(@Param("bookId")Integer bookId,@Param("status")Integer status);
	
	/**
	 * 更新开发版的当前版本号
	 * @param bookId
	 * @param status
	 */
	public void updateBook(@Param("bookId")Integer bookId,@Param("version")Integer version);
	 
	 /**
	 * @param bookId
	 * @return
	 */
	public BookBranchEntity findBookVersion(@Param("bookId")Integer bookId);
	
	public BookBranchEntity findBookBranch(@Param("bookId")Integer bookId);

}
