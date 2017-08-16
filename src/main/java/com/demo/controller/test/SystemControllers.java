package com.demo.controller.test;


public class SystemControllers {/*
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BaseMongoDao dao;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@Autowired
	private MongoFileDao fileStorageDao;
	
	@RequestMapping("/login")
	public Result<?> login(HttpServletRequest request ,HttpServletResponse response){
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		if(null==userName||userName.equals("")||null==password||password.equals("")){
			UserEntity user = new UserEntity();
 			user.setBirthday(new Date());
 			user.setDegree(11);
 			user.setEmail("wanglizong@meizu.com");
			dao.save(user);
			return ResultObject.successMessage("用户名或密码不能为空!") ;
		}
		 UserEntity userCur = userService.findUserLogin(userName, password);
		if(null == userCur){
			return ResultObject.successMessage("户名或密码错误") ;
		}else{
			//request.getSession().setAttribute("userCur_"+userCur.getId(), userCur);
			return  ResultObject.successObject(userCur) ;
		}
		
	 }
	
	
	@RequestMapping("/redisTest")
	public void redisTest(HttpServletRequest request ,HttpServletResponse response ,ModelAndView model){
		    jedisCluster.set("name", "啊芝");
	        String val = jedisCluster.get("name");
	        System.out.println(val);
	}
	
	
	@RequestMapping("/saveFile")
	public void saveFile(HttpServletRequest request ,HttpServletResponse response ,ModelAndView model){
		

		  InputStream streamToUploadFrom = null;
		  try {
		    streamToUploadFrom = new FileInputStream(new File("E:/my.png"));

		   DBObject metaData = new BasicDBObject();
		   metaData.put("brand", "Audi");
		   metaData.put("model", "Audi A3");
		   metaData.put("description","Audi german automobile manufacturer that designs, engineers, and distributes automobiles");

		   String id = fileStorageDao.store(streamToUploadFrom,"audi.jpg", "image/jpeg", metaData);

		   System.out.println("Find By Id----------------------");
		   GridFSDBFile byId = fileStorageDao.getById(id);
		   System.out.println("File Name:- " + byId.getFilename());
		   System.out.println("Content Type:- " + byId.getContentType());
		   
		   
		   
		   System.out.println("Find By Filename----------------------");
		   GridFSDBFile byFileName = fileStorageDao.getByFilename("audi.jpg");
		   System.out.println("File Name:- " + byFileName.getFilename());
		   System.out.println("Content Type:- " + byFileName.getContentType());
		   
		   
		   System.out.println("List All Files----------------------");
		   for (GridFSDBFile file : fileStorageDao.findAll()) {
		    System.out.println("File Name:- " + file.getFilename());
		    System.out.println("Content Type:- " + file.getContentType());
		    System.out.println("Meta Data Brand:- " + file.getMetaData().get("brand"));
		    System.out.println("Meta Data Model:- " + file.getMetaData().get("model"));
		    System.out.println("Meta Data Description:- " + file.getMetaData().get("description"));
		   }
		   
		   GridFSDBFile retrive = fileStorageDao.retrive("audi.jpg");
		   retrive.writeTo("c:\\newaudi.jpg");
		   
		  } catch (BeansException e) {
		   System.out.println("BeansException:-" + e.getMessage());
		  } catch (IOException e) {
		   System.out.println("IOException:-" + e.getMessage());
		  } finally {
		   if (streamToUploadFrom != null) {
		    try {
		    	streamToUploadFrom.close();
		    } catch (IOException e) {
		     System.out.println("IOException Finally:-" + e.getMessage());
		    }
		   }
		  }

	}
	

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request ,HttpServletResponse response ,ModelAndView model){
		HttpSession session = request.getSession(false);//防止创建Session
		String userId = request.getParameter("userId");
		if(null == session){
			model.setViewName("/view/login");	
		}else{
			request.getSession().removeAttribute("userCur_"+userId);
			model.setViewName("/view/login");
		}
		return model;
	}
	
*/}
