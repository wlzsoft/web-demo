package com.demo.dao;

import org.springframework.stereotype.Repository;

import com.pmp.entity.BookPriceEntity;


@Repository
public interface BookPriceDao {
	//练习本价格新增
	public void addBookPrice(BookPriceEntity entity);
	
	
	//练习本价格修改
	public void updateBookPrice(BookPriceEntity entity);
	
}
