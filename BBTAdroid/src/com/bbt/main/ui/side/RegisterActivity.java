package com.bbt.main.ui.side;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SignUpCallback;
import com.bbt.main.bean.User;
import com.bbt.main.dao.UserDao;
import com.bbt.main.tool.HttpClientHelper;
import com.bbt.main.ui.R;
import com.bbt.main.ui.R.id;
import com.bbt.main.ui.R.layout;

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
 	public class RegisterActivity extends Activity{
 	private static final String URL="http://192.168.191.1:8080/BBTWebServer/register";
	public static final String TAG = "register";
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if(msg.what==0x123)
			{
				Toast.makeText(RegisterActivity.this, "×¢²á³É¹¦", Toast.LENGTH_SHORT).show();
			}
			else if(msg.what==0x124){
				Toast.makeText(RegisterActivity.this, "×¢²áÊ§°Ü", Toast.LENGTH_SHORT).show();
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		initView();
	}
	private void initView() {
		final RadioButton btnNan = (RadioButton) findViewById(R.id.register_radiobtn_nan);
		final RadioButton btnNv = (RadioButton) findViewById(R.id.register_radiobtn_nv);
		final EditText editnickname=(EditText) findViewById(R.id.nickname);
	   final EditText editPhoneNum = (EditText)	findViewById(R.id.register_edit_phonenum);
	   final EditText editPhoneReadName = (EditText)		findViewById(R.id.register_radiobtn_realname);
	   final EditText editPhoneStunum = (EditText)		findViewById(R.id.register_radiobtn_stunum);
	   final EditText editPhonePassword = (EditText)	findViewById(R.id.register_radiobtn_password);
	   findViewById(R.id.register_btn_submit).setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			String sex = "f";
			if(btnNan.isChecked()){
				sex = "ÄÐ";
			}else if(btnNv.isChecked()){
				sex = "Å®";
			}
			final Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", editnickname.getText().toString());
			map.put("phone", editPhoneNum.getText().toString());
			map.put("realname", editPhoneReadName.getText().toString());
			map.put("stuNum", editPhoneStunum.getText().toString());
			map.put("pwd", editPhonePassword.getText().toString());
			map.put("sex",sex);
			new Thread(new Runnable() {
				@Override
				public void run() {
					byte[] data = HttpClientHelper.doPostSubmit(URL, map);
					System.out.println("data = " + data);
					String jsonString = new String(data);
					try {
						
						JSONObject jsonObject = new JSONObject(jsonString);
						int code = jsonObject.getInt("code");
						if (code ==0) {
							System.out.println("01226454949494");
							handler.sendEmptyMessage(0x123);
						}
						else if(code==11){
							handler.sendEmptyMessage(0x124);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}).start();
			}
	   		});
	}
}
