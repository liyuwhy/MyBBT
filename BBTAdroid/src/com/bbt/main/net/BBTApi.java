package com.bbt.main.net;

import java.io.File;
import java.io.FileNotFoundException;

import org.kymjs.kjframe.KJBitmap;

import com.bbt.main.ui.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.view.View;

public class BBTApi {
	

	public static void displayHeadImg(View view,String imgUrl){
		int index = imgUrl.lastIndexOf("\\");
		String imgIndex = imgUrl.substring(index + 1);
		new KJBitmap().displayWithLoadBitmap(view, ApiHttpClient.getAbsoluteApiUrl("img/" + imgIndex),
				R.drawable.widget_dface);
	}
	

	public static void updatePortrait(String phone, File portrait, AsyncHttpResponseHandler handler)
			throws FileNotFoundException {
		RequestParams params = new RequestParams();
		params.put("phone", phone);
		params.put("icon", portrait);
		ApiHttpClient.post("iconModify", params, handler);
	}

	public static void loginToServer(String phone, String password, AsyncHttpResponseHandler handler) {
		RequestParams params = new RequestParams();
		params.put("phone", phone);
		params.put("password", password);
		ApiHttpClient.post("login", params, handler);
	}

	public static void registerToServer(RequestParams params, AsyncHttpResponseHandler handler) {
		ApiHttpClient.post("register", params, handler);
	}
	
	//发单
	public static void sendOrder(RequestParams params,AsyncHttpResponseHandler handler){
		ApiHttpClient.post("SendOrder", params, handler);
	}
	
	
	public static void getDefaultOrder(AsyncHttpResponseHandler handler){
		ApiHttpClient.get("SelectOrdersByDate", handler);
	}
	
	

}
