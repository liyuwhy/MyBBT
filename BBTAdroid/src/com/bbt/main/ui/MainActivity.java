package com.bbt.main.ui;

import java.io.IOException;
import com.avos.avoscloud.okhttp.Call;
import com.avos.avoscloud.okhttp.Callback;
import com.avos.avoscloud.okhttp.OkHttpClient;
import com.avos.avoscloud.okhttp.Request;
import com.avos.avoscloud.okhttp.Response;
import com.bbt.main.bean.User;
import com.bbt.main.tool.SPUtil;
import com.bbt.main.ui.fragment.HomeFragment;
import com.bbt.main.ui.fragment.MainFragment;
import com.bbt.main.view.CircleImageView;
import com.bbt.main.view.SlidingMenu;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	public static final String TAG = "MainActivity";

	public static final String PARAM_FRAGMENT = "param_fragmennt";

	private Fragment[] fragments;
	private Button[] btns;
	private final int[] normalDrawable = new int[] { R.drawable.home, R.drawable.icon_show, R.drawable.chat,
			R.drawable.icon_time };
	private final int[] pressDrawable = new int[] { R.drawable.home_press, R.drawable.show_press, R.drawable.chat,
			R.drawable.icon_time };
	int currentFragment = 0;

	private SlidingMenu mMenu;

	private CircleImageView circleImgView;

	private TextView txtName, txtPhonenumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_test);

		initView();
		mMenu = (SlidingMenu) findViewById(R.id.id_menu_layout);
		initFragment();

		initUserInfo();
		// 初始化默认图片
		getDefaultImgHead();
	}

	@Override
	protected void onResume() {
		super.onResume();
		initUserInfo();
	}

	private void initUserInfo() {
		User user = new SPUtil(this).getUserInfo();
		if (user == null) {
			return;
		}
		txtName.setText(user.getRealname());
		txtPhonenumber.setText(user.getPhoneNum());
	}

	private void getDefaultImgHead() {
		// 创建okHttpClient对象
		OkHttpClient mOkHttpClient = new OkHttpClient();
		// 创建一个Request
		final Request request = new Request.Builder().url("http://ac-ymmdgvbn.clouddn.com/9c129acb2b623b18.jpg")
				.build();
		// new call
		Call call = mOkHttpClient.newCall(request);
		// 请求加入调度
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Request request, IOException e) {
			}

			@Override
			public void onResponse(final Response response) throws IOException {

				// String htmlStr = response.body().string();
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						Bitmap bimap = null;
						try {
							bimap = BitmapFactory.decodeStream(response.body().byteStream());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (bimap == null)
							return;
						circleImgView.setmSrc(bimap);
					}
				});
			}
		});
	}

	private void initView() {
		Button btnHome = (Button) findViewById(R.id.id_btn_home);
		Button btnNews = (Button) findViewById(R.id.id_btn_show);
		Button btnShow = (Button) findViewById(R.id.id_btn_show);
		Button btnTime = (Button) findViewById(R.id.id_btn_time);
		btns = new Button[] { btnHome, btnNews, btnShow, btnTime };

		circleImgView = (CircleImageView) findViewById(R.id.menu_circle_img);

		txtName = (TextView) findViewById(R.id.menu_info_name);
		txtPhonenumber = (TextView) findViewById(R.id.menu_info_phonenumber);
	}

	public void onMainClick(View v) {
		Log.d(TAG, "+++onMainClick");
		switch (v.getId()) {
		case R.id.id_btn_home:
			changeFragment(0);
			break;
		case R.id.id_btn_show:
			changeFragment(1);
			break;
		case R.id.id_btn_time:
			changeFragment(2);
			break;
		case R.id.id_btn_news:
			changeFragment(3);
			break;
		// 点击发订单 -- 从下面滑动出来界面
		case R.id.id_btn_order:
			Intent intent = new Intent();
			intent.setClass(this, ActionOrderActivity.class);
			startActivity(intent);
			// 设置切换动画，从右边进入，左边退出
			overridePendingTransition(R.anim.top_to_bottom, android.R.anim.fade_out);
			break;
		case R.id.menu_circle_img:
			Intent intentLogin = new Intent();
			intentLogin.setClass(this, LoginActivity.class);
			startActivity(intentLogin);
			break;
		default:
			break;
		}
	}

	private void changeFragment(int which) {
		Log.d(TAG, "onChangeFragment" + which);
		getSupportFragmentManager().beginTransaction().hide(fragments[currentFragment]).show(fragments[which]).commit();
		currentFragment = which;

		btns[0].setBackgroundResource(R.drawable.home);
		btns[1].setBackgroundResource(R.drawable.icon_show);

		btns[which].setBackgroundResource(pressDrawable[which]);

	}

	private void initFragment() {
		Bundle bundle = new Bundle();

		Fragment fragment1 = new HomeFragment();

		MainFragment fragment2 = new MainFragment(1);

		MainFragment fragment3 = new MainFragment(2);

		MainFragment fragment4 = new MainFragment(3);

		fragments = new Fragment[] { fragment1, fragment2, fragment3, fragment4 };

		getSupportFragmentManager().beginTransaction().add(R.id.id_frame_layout, fragment1)
				.add(R.id.id_frame_layout, fragment2).add(R.id.id_frame_layout, fragment3)
				.add(R.id.id_frame_layout, fragment4).hide(fragment1).hide(fragment2).hide(fragment3).hide(fragment4)
				.show(fragment2).commit();
	}

	public void toggleMenu(View view) {
		mMenu.toggle();
	}

}
