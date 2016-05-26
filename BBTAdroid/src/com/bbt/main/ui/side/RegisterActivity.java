package com.bbt.main.ui.side;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SignUpCallback;
import com.bbt.main.base.BaseActivity;
import com.bbt.main.bean.User;
import com.bbt.main.dao.UserDao;
import com.bbt.main.net.BBTApi;
import com.bbt.main.tool.HttpClientHelper;
import com.bbt.main.ui.R;
import com.bbt.main.ui.R.id;
import com.bbt.main.ui.R.layout;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract.CommonDataKinds.Nickname;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends BaseActivity {

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
				if (code.equals("200")) {
					System.out.println("01226454949494");
					Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
				} else if (code.equals("101")) {
					Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	};



	@Override
	public void onClick(View v) {
		
	}

	@Override
	public void initView() {
		final RadioButton btnNan = (RadioButton) findViewById(R.id.register_radiobtn_nan);
		final RadioButton btnNv = (RadioButton) findViewById(R.id.register_radiobtn_nv);
		final EditText editnickname = (EditText) findViewById(R.id.nickname);
		final EditText editPhoneNum = (EditText) findViewById(R.id.register_edit_phonenum);
		final EditText editPhoneReadName = (EditText) findViewById(R.id.register_radiobtn_realname);
		final EditText editPhoneStunum = (EditText) findViewById(R.id.register_radiobtn_stunum);
		final EditText editPhonePassword = (EditText) findViewById(R.id.register_radiobtn_password);
		findViewById(R.id.register_btn_submit).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String sex = "f";
				if (btnNan.isChecked()) {
					sex = "男";
				} else if (btnNv.isChecked()) {
					sex = "女";
				}
				final Map<String, Object> map = new HashMap<String, Object>();

				RequestParams params = new RequestParams();
				params.put("name", editnickname.getText().toString());
				params.put("phone", editPhoneNum.getText().toString());
				params.put("realname", editPhoneReadName.getText().toString());
				params.put("stuNum", editPhoneStunum.getText().toString());
				params.put("pwd", editPhonePassword.getText().toString());
				params.put("sex", sex);
				
				showWaitDialog();
				BBTApi.registerToServer(params, handler);

			}
		});
		
	}

	@Override
	public void initData() {
	}

	@Override
	protected int getLayoutId() {
		return R.layout.register;
	}
}
