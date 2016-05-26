package com.bbt.main.base;

import com.avos.avoscloud.AVOSCloud;
import com.bbt.main.net.ApiHttpClient;
import com.loopj.android.http.AsyncHttpClient;

import android.app.Application;

public class BBTApp extends Application{
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		//初始化
		ApiHttpClient.client = new AsyncHttpClient();
		AVOSCloud.initialize(this, "ymmdgvbnxkxtgmbg4k4iw0o47utc0h57r5lh4f4otxlct2ny", "geioho2v3r2cdmdzsji3cr34lospqaa28jahyzvx6zvnv5zi");
	}

}
