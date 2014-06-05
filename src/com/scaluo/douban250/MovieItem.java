package com.scaluo.douban250;

public class MovieItem {
	private int mid;
	private String name;
	private String orgName;
	private String score;
	private int pubYear;
	private String imgSmall;
	private int order;
	
	public MovieItem(int order,int mid,String name,String orgName,String score,int pubYear,String imgSmall){
		this.mid = mid;
		this.name = name;
		this.orgName = orgName;
		this.score = score;
		this.pubYear = pubYear;
		this.imgSmall = imgSmall;
		this.order = order;
	}
	public int getOrder(){
		return order;
	
	}
	public int getMid(){
		return mid;
	}
	public String getName(){
		return name;
	}
	
	public String getOrgName(){
		return orgName;
	}
	
	public String getScore(){
		return score;
	}
	
	public int getPubYear(){
		return pubYear;
	}
	
	public String getImgSmall(){
		return imgSmall;
	}
	
}
