package com.bbt.main.ui;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.avos.avoscloud.LogUtil.log;
import com.bbt.main.tool.PictureUtil;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
public class ActionOrderActivity extends Activity{
	public static final String TAG = "ActionOrderActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_order);
	}
	public void onCancelClick(View v){
		log.d(TAG,"onOrderClick");
		finish();
	    overridePendingTransition(R.anim.no_push,R.anim.bottom_to_top);  
	}
	public void onOrderBtn(View v){
		log.d(TAG,"onOrderClick btn");
		switch (v.getId()) {
		case R.id.order_btn:
			Intent intent = new Intent(this,OrderActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		
	}



}
