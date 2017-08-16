package com.demo.controller.test;


public class MongoDBConnet {/*
	
	@RequestMapping("/mongo")
	public void conn(){
		
		
		 * 1���������ݿ�����
		 
        try {
			 MongoClient mongoClient = new MongoClient("172.16.180.85",27017);
			//ȡ�����ݿ����
			 MongoDatabase db=mongoClient.getDatabase("excercise");
			
			String collectionName="files";
			
			//�������ݿ������GridFS����
			GridFSBucket  gridFSBucket= GridFSBuckets.create(db,collectionName);	
			
			InputStream streamToUploadFrom = new FileInputStream(new File("E:/my.png"));
			
		    GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(358400).metadata(new Document("type", "presentation"));

		    ObjectId fileId = gridFSBucket.uploadFromStream("my002.png", streamToUploadFrom, options);
			System.out.println("============="+fileId.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		    System.out.println("�洢�ļ�ʱ�������󣡣���");
		}
		
		
		
		 * 3������id��ѯ�ϴ��ļ�
		 
		GridFSDBFile findOne= gridFS.findOne(new BasicDBObject("_id",createFile.getId()));
		System.out.print(findOne);
		
		
		 * 4����ѯ�����ļ��б�
		 * DBCursor ���ݿ��α�
		 
		DBCursor fileList=gridFS.getFileList();
		while(fileList.hasNext())
		{
			System.out.print(fileList.next());
		}
		
		
		 *5�� ɾ���ļ�
		 
		gridFS.remove(new  BasicDBObject("_id",createFile.getId()));
		
	
	}
	
	@RequestMapping("/coll")
	public void getCollections(){

		
		
	      try{   
	          // ���ӵ� mongodb ����
	    	  MongoClient mongoClient = new MongoClient("172.16.180.88",27017);
			//ȡ�����ݿ����
	    	  MongoDatabase  mongoDatabase = mongoClient.getDatabase("excercise"); 

	    	  
	    	  
	          MongoCollection collection = mongoDatabase.getCollection("test");
	          System.out.println("���� test ѡ��ɹ�");
	          //�����ĵ�  
	          *//** 
	          * 1. �����ĵ� org.bson.Document ����Ϊkey-value�ĸ�ʽ 
	          * 2. �����ĵ�����List<Document> 
	          * 3. ���ĵ����ϲ������ݿ⼯���� mongoCollection.insertMany(List<Document>) ���뵥���ĵ������� mongoCollection.insertOne(Document) 
	          * *//*
	          
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
	          Document document = new Document("title", "MongoDB").  
	        	         append("description", "database").  
	        	         append("likes", 100).  
	        	         append("by", "Fly");  
	         			
	          List<Document> documents = new ArrayList<Document>();  
	          documents.add(document);  
	          collection.insertMany(documents); 
	          System.out.println("�ĵ�����ɹ�");
	       }catch(Exception e){
	          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	       }
		
	
	}
*/}
