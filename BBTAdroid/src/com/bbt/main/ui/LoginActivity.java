package com.bbt.main.ui;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.bbt.main.bean.User;
import com.bbt.main.dao.UserDao;
import com.bbt.main.tool.SPUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity{
	private Intent intent;
	public static final String TAG = "login";
	private EditText editPhone;
	private EditText editPassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		editPhone = (EditText)findViewById(R.id.login_edit_phone);
		editPassword = (EditText)findViewById(R.id.login_edit_password);
	}
	
	
	public void onMainClick(View v){
		Log.d(TAG, "+++onMainClick");
		switch (v.getId()) {
			case R.id.tv_register:
				intent =new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(intent);
				break;
			case R.id.login_btn_submit:
				Log.d(TAG, "onClick_button_submit");
				UserDao.login(editPhone.getText().toString(), editPassword.getText().toString(), new LogInCallback<AVUser>() {
					
					@Override
					public void done(AVUser avUser, AVException arg1) {
						if( avUser == null){
							Toast.makeText(LoginActivity.this, "登录失败"+arg1.getMessage(), 1000 * 5).show();
							return;
						}
						User user = User.parseToMyUser(avUser);
						Toast.makeText(LoginActivity.this, "登录成功"+user.getUsername(), 1000 * 5).show();
						//保存用户信息
						new SPUtil(LoginActivity.this).saveUserInfo(user);
					}
				});
				break;
			default:
				break;
			}
		}
	}
