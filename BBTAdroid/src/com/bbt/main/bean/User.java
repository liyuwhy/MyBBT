package com.bbt.main.bean;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;

public class User {

	// ? 怎么用用户名和phonenum一致
	
	public static final String SEX = "sex";
	public static final String STU_NUM = "stu_num";
	//昵称
	public static final String Person_Name = "person_name";
	
	

	// 用户名
	private String username;

	// 密码
	private String password;
	// 电话号码
	private String phoneNum;
	// 性别
	private String sex;
	private String realname;
	// 学号
	private String stuNum;
	
	public User() {
	
	}
	
	
	public User(String username, String password, String phoneNum, String sex, String realname, String stuNum) {
		super();
		this.username = username;
		this.password = password;
		this.phoneNum = phoneNum;
		this.sex = sex;
		this.realname = realname;
		this.stuNum = stuNum;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	public static AVUser parseToAVUser(User user) {
		AVUser avUser = new AVUser();
		// 使用电话号码当用户名
		avUser.setUsername(user.getUsername());
		avUser.setPassword(user.getPassword());
		avUser.setMobilePhoneNumber(user.getPhoneNum());
		avUser.put(SEX, user.getSex());
		avUser.put(STU_NUM, user.getStuNum());
 		avUser.put(Person_Name, user.getRealname());
		return avUser;
	}


	public static User parseToMyUser(AVUser avUser){
	     User user = new User();
	     user.setUsername(avUser.getUsername());
	     user.setSex(avUser.getString(SEX));
	     user.setStuNum(avUser.getString(STU_NUM));
	     user.setRealname(avUser.getString(Person_Name));
	     return user;
	}
} 
