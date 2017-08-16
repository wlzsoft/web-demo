package com.demo.util.enums;

public enum LearningCycle {
	
	/**
	 * 第一次练习
	 */
	FIRST_TIME(3) ,
	
	/**
	 * 第二次练习
	 */
	SECOND_TIME(6),
	
	
	/**
	 * 第三次练习
	 */
	Third_TIME(12),
	
	
	/**
	 * 第四次练习
	 */
	FOURTH_TIME(24),
	
	
	/**
	 * 第五次练习
	 */
	FIFTH_TIME(48),

	
	/**
	 * 第六次练习
	 */
	SIXTH_TIME(96),
	
	
	/**
	 * 第七次练习
	 */
    SEVENTH_TIME(192),	

    
	/**
	 * 第八次练习
	 */
    EIGHTH_TIME(384),
    
    
	/**
	 * 第九次练习
	 */
    NINTH_TIME(764),
    
    
	/**
	 * 第十次练习
	 */
    TENTH_TIME(1536),
    
    
	/**
	 * 第十一次练习
	 */
    ELEVENTH_TIME(3072),
    
    
	/**
	 * 第十二次练习
	 */
    TWELFTH_TIME(6144),
    
    
	/**
	 * 第十三次练习
	 */
    THIRTEENTH_TIME(12288),
	
    
	/**
	 * 第十四次练习
	 */
    FOURTEENTH_TIME(24576);
	
    
	public final int timesanmp;
	 
	LearningCycle(int timesanmp){
		this.timesanmp=timesanmp;
	}

}
