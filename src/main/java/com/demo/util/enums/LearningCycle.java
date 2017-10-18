package com.demo.util.enums;

public enum LearningCycle {
	
	/**
	 * 第一次练习1天
	 */
	FIRST_TIME(24) ,
	
	/**
	 * 第二次练习2天
	 */
	SECOND_TIME(48),
	
	
	/**
	 * 第三次练习4天
	 */
	Third_TIME(96),
	
	
	/**
	 * 第四次练习8天
	 */
	FOURTH_TIME(24),
	
	
	/**
	 * 第五次练习16天
	 */
	FIFTH_TIME(48),

	
	/**
	 * 第六次练习32天
	 */
	SIXTH_TIME(96),
	
	
	/**
	 * 第七次练习64天
	 */
    SEVENTH_TIME(192),	

    
	/**
	 * 第八次练习128天
	 */
    EIGHTH_TIME(384),
    
    
	/**
	 * 第九次练习256天
	 */
    NINTH_TIME(764);
	
    
	public final int timesanmp;
	 
	LearningCycle(int timesanmp){
		this.timesanmp=timesanmp;
	}

}
