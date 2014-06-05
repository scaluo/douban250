package com.scaluo.douban250;

public class Movie {
	private String name;
	private String summary;
	private String cover;
	public Movie(String name,String summary,String cover){
		this.name = name;
		this.summary = summary;
		this.cover = cover;
	}
	public String getName(){
		return name;
	}
	
	public String getSummary(){
		return summary;
	}
	
	public String getCover(){
		return cover;
	}
}
