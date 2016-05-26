package com.bbt.main.tool;



import com.avos.avoscloud.LogUtil.log;
import com.bbt.json.bean.User;

import android.Manifest.permission;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SPUtil {

	public static final String SP_NAME = "share_bbt";
	public static final String SP_IS_Login = "is_login";
	public static final String SP_User_name = "username";
	public static final String SP_Phone_number = "phonenumber";
	public static final String SP_USER_ICON = "user_icon";
	
	public static final String SP_User_Id = "user_id";
	
	SharedPreferences preferences;
	private Editor editor;

	public SPUtil(Context context) {
		preferences = context.getSharedPreferences(SP_NAME, Activity.MODE_PRIVATE);
		editor = preferences.edit();
	}

	public void setIsLogin(boolean isLogin) {

		editor.putBoolean(SP_IS_Login, isLogin);
		editor.commit();
	}

	public boolean getIsLogin() {
		return preferences.getBoolean(SP_IS_Login, false);
	}

	public void setUserName(String username) {
		editor.putString(SP_User_name, username);
		editor.commit();
	}

	public String getUserName() {
		return preferences.getString(SP_User_name, "default");
	}

	public void setPhoneNumber(String phoneNumber) {
		editor.putString(SP_Phone_number, phoneNumber);
		editor.commit();
	}

	public String getPhoneNumber() {
		return preferences.getString(SP_Phone_number, "11111");
	}
	
	
	public void setUserId(int userId){
		editor.putInt(SP_User_Id, userId);
		editor.commit();
	}
	
	public int getUserId(){
		return preferences.getInt(SP_User_Id, 0);
	}
	
	
	public void setUserIcon(String iconUrl){
		editor.putString(SP_USER_ICON, iconUrl);
		editor.commit();
	}
	
	public String getUserIcon(){
		return preferences.getString(SP_USER_ICON, "");
	}

	public void saveUserInfo(User user) {
		setIsLogin(true);
		setUserName(user.getName());
		setPhoneNumber(user.getPhone());
		setUserId(user.getUserId());
		setUserIcon(user.getHeadIcon());
	}
	
	

	/**
	 * 
	 * @return if user didn't login,return null;
	 */
	public User getUserInfo() {
		if (!getIsLogin()) {
			return null;
		}
		User user = new User();
		user.setPhone(getPhoneNumber());
		log.d("TAG", "sp"+ getUserName());
		user.setName(getUserName());
		log.d("TAG","name"+ user.getName());
		user.setUserId(getUserId());
		user.setHeadIcon(getUserIcon());
		return user;
	}

}
