package com.lsq.model;

public class Faculty {

	private int id;
	private String facultyName;
	private String facultyDesc;

	public Faculty() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Faculty(String facultyName, String facultyDesc) {
		super();
		this.facultyName = facultyName;
		this.facultyDesc = facultyDesc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getFacultyDesc() {
		return facultyDesc;
	}

	public void setFacultyDesc(String facultyDesc) {
		this.facultyDesc = facultyDesc;
	}

}
