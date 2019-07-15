package com.lsq.model;

public class Dormitory {

	private int id;
	private String dormitoryName;
	private String dormitoryDesc;

	public Dormitory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dormitory(String dormitoryName, String dormitoryDesc) {
		super();
		this.dormitoryName = dormitoryName;
		this.dormitoryDesc = dormitoryDesc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDormitoryName() {
		return dormitoryName;
	}

	public void setDormitoryName(String dormitoryName) {
		this.dormitoryName = dormitoryName;
	}

	public String getDormitoryDesc() {
		return dormitoryDesc;
	}

	public void setDormitoryDesc(String dormitoryDesc) {
		this.dormitoryDesc = dormitoryDesc;
	}

}
