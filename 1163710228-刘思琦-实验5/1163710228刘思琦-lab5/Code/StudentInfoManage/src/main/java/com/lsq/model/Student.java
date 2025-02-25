package com.lsq.model;

import java.util.Date;

public class Student {

	private int stuId;
	private String stuNo;
	private String stuName;
	private String sex;
	private Date birthday;
	private int facultyId = -1;
	private int gradeId = -1;
	private int dormitoryId = -1;
	private String email;
	private String stuDesc;
	private String gradeName;

	public Student() {
		super();
	}

	public Student(String stuNo, String stuName, String sex, Date birthday, int facultyId, int gradeId, int dormitoryId,
			String email, String stuDesc) {
		super();
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.sex = sex;
		this.birthday = birthday;
		this.facultyId = facultyId;
		this.gradeId = gradeId;
		this.dormitoryId = dormitoryId;
		this.email = email;
		this.stuDesc = stuDesc;
	}

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	
	public int getDormitoryId() {
		return dormitoryId;
	}

	public void setDormitoryId(int dormitoryId) {
		this.dormitoryId = dormitoryId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStuDesc() {
		return stuDesc;
	}

	public void setStuDesc(String stuDesc) {
		this.stuDesc = stuDesc;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

}
