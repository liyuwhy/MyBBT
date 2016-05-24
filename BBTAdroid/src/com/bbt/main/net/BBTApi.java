package com.bbt.main.net;

import java.io.File;
import java.io.FileNotFoundException;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class BBTApi {

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
	
	//·¢µ¥
	public static void sendOrder(RequestParams params,AsyncHttpResponseHandler handler){
		ApiHttpClient.post("SendOrder", params, handler);
	}

}
