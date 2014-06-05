package com.scaluo.douban250;

public class Movie {
	private String name;
	private String summary;
	public Movie(String name,String summary){
		this.name = name;
		this.summary = summary;
	}
	public String getName(){
		return name;
	}
	
	public String getSummary(){
		return summary;
	}
}
