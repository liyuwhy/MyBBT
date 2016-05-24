package com.bbt.main.ui.side;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.bbt.main.bean.User;
import com.bbt.main.dao.UserDao;
import com.bbt.main.net.BBTApi;
import com.bbt.main.tool.HttpClientHelper;
import com.bbt.main.tool.SPUtil;
import com.bbt.main.ui.R;
import com.bbt.main.ui.R.id;
import com.bbt.main.ui.R.layout;
import com.loopj.android.http.AsyncHttpResponseHandler;

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

public class LoginActivity extends Activity {
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

			String jsonString = new String(data);
			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				String code = jsonObject.getString("code");
				System.out.println(jsonObject.toString());
				if (code.equals("200")) {
					Toast.makeText(LoginActivity.this, "µÇÂ¼³É¹¦", Toast.LENGTH_SHORT).show();
					// getjson
					// share
				} else if (code.equals("101")) {
					Toast.makeText(LoginActivity.this, "ÃÜÂë´íÎó", Toast.LENGTH_SHORT).show();
				} else if (code.equals("102")) {
					Toast.makeText(LoginActivity.this, "ÕËºÅ²»´æÔÚ", Toast.LENGTH_SHORT).show();
				}
				// else if()
				// {
				// handler.sendEmptyMessage(0x126);
				// }
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initview();
	}

	private void initview() {
		editPhone = (EditText) findViewById(R.id.login_edit_phone);
		editPassword = (EditText) findViewById(R.id.login_edit_password);
	}

	public void onMainClick(View v) {
		Log.d(TAG, "+++onMainClick");
		switch (v.getId()) {
		case R.id.login_cancel:
			finish();
			break;
		case R.id.tv_register:
			intent = new Intent(LoginActivity.this, RegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.login_btn_submit:
			BBTApi.loginToServer(editPhone.getText().toString(), editPassword.getText().toString(), handler);
			break;

		}
	}
}
