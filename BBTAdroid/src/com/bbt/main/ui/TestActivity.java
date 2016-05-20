package com.bbt.main.ui;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.SignUpCallback;
import com.bbt.main.tool.DataToVOS;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestActivity extends FragmentActivity{
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.test_activity);
	/*	DataToVOS.testGet(new SignUpCallback() {
			
			@Override
			public void done(AVException e) {
				if (e == null) {
	                 // 验证成功
				   Toast.makeText(TestActivity.this, "发送验证成功", 1000*5).show();
	             } else {
	                 Toast.makeText(TestActivity.this, "发送验证失败", 1000*5).show();
	             }
			}
		});*/
	}
	
	
	public void onClickView(View view){
		switch (view.getId()) {
		case R.id.id_verify_btn:
			String verifyCode = ((EditText)findViewById(R.id.id_input_verifycode)).getText().toString();
			DataToVOS.testVerifyIn(verifyCode, new AVMobilePhoneVerifyCallback() {
				
				@Override
				public void done(AVException e) {
					   if (e == null) {
			                 // 验证成功
						   Toast.makeText(TestActivity.this, "验证成功", 1000*5).show();
			             } else {
			                 Toast.makeText(TestActivity.this, "验证失败", 1000*5).show();
			             }
					
				}
			});
			
			break;

		default:
			break;
		}
	}
	

}
