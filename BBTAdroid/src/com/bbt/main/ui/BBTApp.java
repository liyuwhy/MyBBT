package com.bbt.main.ui;

import com.avos.avoscloud.AVOSCloud;

import android.app.Application;

public class BBTApp extends Application{
	
	@Override
	public void onCreate() {
		super.onCreate();
		AVOSCloud.initialize(this, "ymmdgvbnxkxtgmbg4k4iw0o47utc0h57r5lh4f4otxlct2ny", "geioho2v3r2cdmdzsji3cr34lospqaa28jahyzvx6zvnv5zi");
	}

}
