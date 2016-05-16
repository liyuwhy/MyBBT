package com.bbt.main.ui;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.bbt.main.bean.User;
import com.bbt.main.dao.UserDao;
import com.bbt.main.tool.HttpClientHelper;
import com.bbt.main.tool.SPUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceActivity.Header;
import android.provider.SyncStateContract.Helpers;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity{
	private Intent intent;
	public static final String TAG = "login";
	protected static final String URL = "http://192.168.191.1:8080/BBTWebServer/result";
	private EditText editPhone;
	private EditText editPassword;
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if(msg.what==0x123)
			{
			Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
			}
			else if(msg.what==0x124)
			{
				Toast.makeText(LoginActivity.this, "密码错误",Toast.LENGTH_SHORT).show();
			}
			else if(msg.what==0x125)
			{
				Toast.makeText(LoginActivity.this, "账号不存在",Toast.LENGTH_SHORT).show();
			}
			else if(msg.what==0x126)
			{
				Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initview();
	}
	private void initview() {
		// TODO Auto-generated method stub
		
		editPhone = (EditText)findViewById(R.id.login_edit_phone);
		editPassword = (EditText)findViewById(R.id.login_edit_password);
	}
//		findViewById(R.id.login_btn_submit).setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		final Map<String,Object> map=new HashMap<String,Object>();
//		map.put("phone", editPhone.getText().toString());
//		map.put("pwd",editPassword.getText().toString());
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				 byte data[]=HttpClientHelper.doPostSubmit(URL, map);
//				 String jsonstring=new String(data);
//				 try {
//						
//						JSONObject jsonObject = new JSONObject(jsonstring);
//						int code = jsonObject.getInt("code");
//						if (code ==11) {
//							System.out.println("01226454949494");
//							handler.sendEmptyMessage(0x123);
//						}
//						else if(code==12){
//							handler.sendEmptyMessage(0x124);
//						}
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//			}
//		}).start();
	public void onMainClick(View v){
		Log.d(TAG, "+++onMainClick");
		switch (v.getId()) {
			case R.id.tv_register:
				intent =new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(intent);
				break;
			case R.id.login_btn_submit:
				new Thread(new Runnable() {
					@Override
				public void run() {
						// TODO Auto-generated method stub
				Log.d(TAG, "onClick_button_submit");
				final Map<String, Object> map = new HashMap<String, Object>();
				map.put("phone", editPhone.getText().toString());
				map.put("password",editPassword.getText().toString());
				byte data[]=HttpClientHelper.doPostSubmit(URL, map);
				String jsonString=new String(data);
				try {
					JSONObject jsonObject = new JSONObject(jsonString);
					String code = jsonObject.getString("code");
					if (code.equals(200)) {
						handler.sendEmptyMessage(0x123);
						//getjson
						//share
						
					}
					else if(code.equals(101)){
						handler.sendEmptyMessage(0x124);
					}
					else if(code.equals(102))
					{
						handler.sendEmptyMessage(0x125);
					}
					else if(code.equals(103))
					{
						handler.sendEmptyMessage(0x126);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
					}
				}).start();
		}
	}
	}
//				
////				UserDao.login(editPhone.getText().toString(), editPassword.getText().toString(), new LogInCallback<AVUser>() {
////					@Override
////					public void done(AVUser avUser, AVException arg1) {
////						if( avUser == null){
////							Toast.makeText(LoginActivity.this, "登录失败"+arg1.getMessage(), 1000 * 5).show();
////							return;
////						}
////						User user = User.parseToMyUser(avUser);
////						Toast.makeText(LoginActivity.this, "登录成功"+user.getUsername(), 1000 * 5).show();
////						//保存用户信息
////						new SPUtil(LoginActivity.this).saveUserInfo(user);
////					}
////				});
//				break;
//			default:
//				break;
//			}
