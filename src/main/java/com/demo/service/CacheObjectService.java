package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.BookDao;
import com.pmp.entity.BookEntity;
import com.smartframe.rediscache.dao.RedisStringDao;
@Service
public class CacheObjectService {
		@Autowired
	    private RedisStringDao redisStringDao;
		
		@Autowired
		private BookDao bookDao;
		
		/**
		 * 获取当前练习本版本号
		 * @param key bookId
		 * @return
		 */
		public String getBookVer(String bookId){
			String key="bookver_"+bookId;
			String value = redisStringDao.get(key).toString();
			if(null==value||"".equals(value)){
				BookEntity entity = bookDao.findBook(Integer.parseInt(bookId));
				if(entity==null){
					redisStringDao.add(key, 0);
				}else{
					redisStringDao.add(key, entity.getVersion());	
				}
				value = entity.getVersion().toString();
			}
			return value;
		}
}
