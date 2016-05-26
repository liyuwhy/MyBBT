package com.bbt.json.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
	/**
	 * 
	 * 
	 * "userInfo": { "active":"0", "credit":"0", "icon":"\\img\\default.jpg",
	 * "money":0, "name":"何", "phone":"13155734537", "realname":"何万川",
	 * "registerTime":"2016-05-24 21:52:43", "sex":"男", "stuNum":"11314010124",
	 * "userId":"20160039" }
	 */

	
	
	public static String JSONMain = "userInfo";
	
	private String headIcon;
	private String phone;
	private String name;
	private String realName;

	private int userId;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getHeadIcon() {
		return headIcon;
	}

	public void setHeadIcon(String headIcon) {
		this.headIcon = headIcon;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public static User parserToUser(String str) throws JSONException {
		JSONObject jsonObject = new JSONObject(str);
		User user = new User();
		user.setHeadIcon(jsonObject.getString("icon"));
		user.setName(jsonObject.getString("name"));
		user.setPhone(jsonObject.getString("phone"));
		user.setRealName(jsonObject.getString("realname"));
		user.setUserId(jsonObject.getInt("userId"));
		return user;
	}

}
