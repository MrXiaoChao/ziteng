package com.example.john.ziteng.citylist;

public class SortModel {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSortLetters() {
		return sortLetters;
	}

	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}


	public SortModel(String name, String cityid) {
		this.name = name;
		this.cityid = cityid;
	}
	private String name;   //显示的数据
	private String sortLetters;  //显示数据拼音的首字母
	private String cityid;
}
