package com.bbt.main.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Login_view extends Activity{
	private Intent intent;
	public static final String TAG = "login";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	}
	public void onMainClick(View v){
		Log.d(TAG, "+++onMainClick");
		switch (v.getId()) {
			case R.id.tv_register:
				intent =new Intent(Login_view.this,register_view.class);
				startActivity(intent);
				break;
			default:
				break;
			}
		}
	}
