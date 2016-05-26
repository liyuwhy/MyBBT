package com.bbt.main.ui.side;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.bbt.json.bean.User;
import com.bbt.main.base.BaseActivity;
import com.bbt.main.net.BBTApi;
import com.bbt.main.tool.SPUtil;
import com.bbt.main.ui.R;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceActivity.Header;
import android.provider.SyncStateContract.Helpers;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Space;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
	private Intent intent;
	public static final String TAG = "login";
	private EditText editPhone;
	private EditText editPassword;

	private AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
		@Override
		public void onFailure(int arg0, org.apache.http.Header[] arg1, byte[] arg2, Throwable arg3) {

		}

		@Override
		public void onSuccess(int arg0, org.apache.http.Header[] arg1, byte[] data) {
			hideWaitDialog();

			String jsonString = new String(data);
			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				String code = jsonObject.getString("code");
				System.out.println(jsonObject.toString());
				if (code.equals("200")) {
					
					
					User user = User.parserToUser(jsonObject.getString(User.JSONMain).toString());
					Toast.makeText(LoginActivity.this, "µÇÂ¼³É¹¦"+user.getHeadIcon(), Toast.LENGTH_SHORT).show();
					new SPUtil(LoginActivity.this).saveUserInfo(user);
					
				} else if (code.equals("101")) {
					Toast.makeText(LoginActivity.this, "ÃÜÂë´íÎó", Toast.LENGTH_SHORT).show();
				} else if (code.equals("102")) {
					Toast.makeText(LoginActivity.this, "ÕËºÅ²»´æÔÚ", Toast.LENGTH_SHORT).show();
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
	};

	@Override
	public void onClick(View v) {
		Log.d(TAG, "+++onMainClick");
		switch (v.getId()) {
		case R.id.login_cancel:
			finish();
			break;
		case R.id.tv_register:
			startActivity(RegisterActivity.class);
			break;
		case R.id.login_btn_submit:
			showWaitDialog();
			BBTApi.loginToServer(editPhone.getText().toString(), editPassword.getText().toString(), handler);
			break;
		}
	}
	
	

	@Override
	public void initView() {
		editPhone = (EditText) findViewById(R.id.login_edit_phone);
		editPassword = (EditText) findViewById(R.id.login_edit_password);

	}



	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.activity_login;
	}



	@Override
	public void initData() {
		
	}
}
