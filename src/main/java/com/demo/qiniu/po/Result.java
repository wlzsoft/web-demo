package com.demo.qiniu.po;

public class Result {
	
    /**
     * 介于0-2间的整数，表示该图像被机器判定为哪个分类，分别对应： 0色情；1性感；2正常，三种分类的具体解释如下。
     *  0色情：有明显的敏感部分裸露的图片，血腥图片，描述性交行为的体位或姿势和色情场景的图片。注意，儿童色情的内容，也被归为0。
     *  1性感：衣着暴露但没有裸露敏感部位。尺类跨度比较大，从露出皮肤较多的图片到性感写真、诱惑自拍等，区分0和1最重要的规则为是否有裸露敏感部位。注意，孕妇自拍露肚子的图也被归为了1。
     *  2正常：非色情，非性感图片
     */
    public int label;
    
    /**
     * 介于0-1间的浮点数，表示该图像被识别为某个分类的概率值，概率越高、机器越肯定；您可以根据您的需求确定需要人工复审的界限
     */
    public float score;
    
    /**
     * 是否需要人工复审该图片，鉴黄服务是否对结果确定。true需要false不需要
     */
    public Boolean review;

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Boolean getReview() {
		return review;
	}

	public void setReview(Boolean review) {
		this.review = review;
	}
    
    
    
}
