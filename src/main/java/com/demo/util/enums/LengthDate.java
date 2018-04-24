package com.demo.util.enums;

/**
 * 记忆等级 (练习间隔时间) 小时
 * @author Administrator
 *
 */
public enum LengthDate {
	
	/**
	 * 等级 1 (1天)
	 */
	ONE_DATE(6) ,
	
	/**
	 * 等级 2 (2天)
	 */
	TWO_DATE(12),
	
	
	/**
	 * 等级 3  (4天)
	 */
	Third_DATE(24),
	
	
	/**
	 * 等级 4 (8天)
	 */
	FOURTH_DATE(48),
	
	
	/**
	 * 等级 5 (16天)
	 */
	FIFTH_DATE(96),

	
	/**
	 * 等级 6 (32天)
	 */
	SIXTH_DATE(192),
	
	
	/**
	 * 等级 7 (64天)
	 */
    SEVENTH_DATE(384),	

    
	/**
	 * 等级 8 (128天)
	 */
    EIGHTH_DATE(764),
    
    
	/**
	 * 等级 9 (256天)
	 */
    NINTH_DATE(1536),
	
	/**
	 * 等级 10 (512天)
	 */
    TEN_DATE(3072),
	
	/**
	 * 等级 11 (1024天)
	 */
    ELEVEN_DATE(6144),
	
	/**
	 * 等级 12 (2018天)
	 */
    TWELVE_DATE(12288);
	
    
	public final int timesanmp;
	 
	LengthDate(int timesanmp){
		this.timesanmp=timesanmp;
	}

}
