package com.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.entity.UserEntity;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.mongodb.gridfs.GridFSInputFile;

@Controller
public class MongoDBConnet {
	
	@RequestMapping("/mongo")
	public void conn(){
		
		/*
		 * 1、创建数据库连接
		 */
        try {
			 MongoClient mongoClient = new MongoClient("172.16.180.88",27017);
			//取得数据库对象
			 MongoDatabase db=mongoClient.getDatabase("excercise");
			
			String collectionName="files";
			
			//创建数据库对象中GridFS集合
			GridFSBucket  gridFSBucket= GridFSBuckets.create(db,collectionName);	
			
			InputStream streamToUploadFrom = new FileInputStream(new File("E:/my.png"));
			
		    GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(358400).metadata(new Document("type", "presentation"));

		    ObjectId fileId = gridFSBucket.uploadFromStream("my002.png", streamToUploadFrom, options);
			System.out.println("============="+fileId.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		    System.out.println("存储文件时发生错误！！！");
		}
/*		
		
		
		 * 3、根据id查询上传文件
		 
		GridFSDBFile findOne= gridFS.findOne(new BasicDBObject("_id",createFile.getId()));
		System.out.print(findOne);
		
		
		 * 4、查询所有文件列表
		 * DBCursor 数据库游标
		 
		DBCursor fileList=gridFS.getFileList();
		while(fileList.hasNext())
		{
			System.out.print(fileList.next());
		}
		
		
		 *5、 删除文件
		 
		gridFS.remove(new  BasicDBObject("_id",createFile.getId()));
		
	*/
	}
	
	@RequestMapping("/coll")
	public void getCollections(){

		
		
	      try{   
	          // 连接到 mongodb 服务
	    	  MongoClient mongoClient = new MongoClient("172.16.180.88",27017);
			//取得数据库对象
	    	  MongoDatabase  mongoDatabase = mongoClient.getDatabase("excercise"); 

	    	  
	    	  
	          MongoCollection collection = mongoDatabase.getCollection("test");
	          System.out.println("集合 test 选择成功");
	          //插入文档  
	          /** 
	          * 1. 创建文档 org.bson.Document 参数为key-value的格式 
	          * 2. 创建文档集合List<Document> 
	          * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document) 
	          * */
	          
	         UserEntity user = new UserEntity();
	         			user.setBirthday(new Date());
	         			user.setDegree(11);
	         			user.setEmail("wanglizong@meizu.com");
	         			JSONObject jo = JSON.parseObject(JSON.toJSONString(user));
	         			Document document = new Document();
	         			Set<Entry<String, Object>> set = jo.entrySet();
	         			for (Entry<String, Object> entry : set) {
	         				document.put(entry.getKey(), entry.getValue());
						}
/*	          Document document = new Document("title", "MongoDB").  
	        	         append("description", "database").  
	        	         append("likes", 100).  
	        	         append("by", "Fly");  */
	         			
	          List<Document> documents = new ArrayList<Document>();  
	          documents.add(document);  
	          collection.insertMany(documents); 
	          System.out.println("文档插入成功");
	       }catch(Exception e){
	          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	       }
		
	
	}
}
