package com.demo.util.enums;

/**
 * 记忆等级 (练习间隔时间) 小时
 * @author Administrator
 *
 */
public enum LearningCycle {
	
	/**
	 * 等级 1 (0.25天)
	 */
	FIRST_TIME(6) ,
	
	/**
	 * 等级 2 (0.5天)
	 */
	SECOND_TIME(12),
	
	
	/**
	 * 等级 3 (1天)
	 */
	Third_TIME(24),
	
	
	/**
	 * 等级 4 (2天)
	 */
	FOURTH_TIME(48),
	
	
	/**
	 * 等级 5 (4天)
	 */
	FIFTH_TIME(96),

	
	/**
	 * 等级 6 (8天)
	 */
	SIXTH_TIME(192),
	
	
	/**
	 * 等级 7 (16天)
	 */
    SEVENTH_TIME(384),	

    
	/**
	 * 等级 8 (32天)
	 */
    EIGHTH_TIME(764),
    
    
	/**
	 * 等级 9 (64天)
	 */
    NINTH_TIME(1536),
	
	/**
	 * 等级 10 (128天)
	 */
    TEN_TIME(3072),
	
	/**
	 * 等级 11 (256天)
	 */
    ELEVEN_TIME(6144),
	
	/**
	 * 等级 12 (512天)
	 */
    TWELVE_TIME(12288);
	
    
	public final int timesanmp;
	 
	LearningCycle(int timesanmp){
		this.timesanmp=timesanmp;
	}

}
