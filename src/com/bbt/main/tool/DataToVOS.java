package com.bbt.main.tool;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;

import android.util.Log;

public class DataToVOS {

	public static void testAVObject() {
		AVObject testObject = new AVObject("TestObject");
		testObject.put("foo", "bar");
		testObject.put("words","Hello World!");
		testObject.saveInBackground(new SaveCallback() {

			@Override
			public void done(AVException e) {
				if (e == null) {
					Log.d("saved", "success!");
				}
			}
		});
	}
	
	public static void testGet(SignUpCallback callBack){
		
		
		 AVOSCloud.requestSMSCodeInBackground("15879245707", new RequestMobileCodeCallback() {
	            @Override
	            public void done(AVException e) {
	                // 发送失败可以查看 e 里面提供的信息
	            }
	        });
		
		
		
		Log.i("hjiang", "afa");
	    AVUser user = new AVUser();
	     user.setUsername("hjiang");
	     user.setPassword("f32@ds*@&dsa");
	     user.setEmail("hang@leancloud.rocks");

	     // 其他属性可以像其他AVObject对象一样使用put方法添加
	     user.put("mobilePhoneNumber", "158-7924-5707");
          
	     user.signUpInBackground(callBack);
		
		
	}
	
	public static void testVerifyIn(String verifyCode,AVMobilePhoneVerifyCallback callBack){
		 AVUser.verifyMobilePhoneInBackground(verifyCode, callBack);
	}
	

}
