package com.bbt.main.ui;

import java.io.IOException;
import com.avos.avoscloud.okhttp.Call;
import com.avos.avoscloud.okhttp.Callback;
import com.avos.avoscloud.okhttp.OkHttpClient;
import com.avos.avoscloud.okhttp.Request;
import com.avos.avoscloud.okhttp.Response;
import com.bbt.main.bean.User;
import com.bbt.main.tool.ActionBarBuilder;
import com.bbt.main.tool.SPUtil;
import com.bbt.main.ui.fragment.HomeFragment;
import com.bbt.main.ui.fragment.MainFragment;
import com.bbt.main.ui.fragment.PresentFragement;
import com.bbt.main.view.CircleImageView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

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

public class MainActivity extends SlidingFragmentActivity {

	public static final String TAG = "MainActivity";

	public static final String PARAM_FRAGMENT = "param_fragmennt";

	private static final String KEY_INDEX = "KEY_INDEX";

	private Fragment[] fragments;
	private Button[] btns;
	private TextView[] txts;
	private final int[] normalDrawable = new int[] { R.drawable.icon_home_normal, R.drawable.icon_show_normal, R.drawable.icon_message_normal,
			R.drawable.icon_doing_normal };
	private final int[] pressDrawable = new int[] { R.drawable.icon_home_press, R.drawable.icon_show_press, R.drawable.icon_message_press,
			R.drawable.icon_doing_press };
	int currentFragment = 0;
	/* private SlidingLayout mMenu; */
	private CircleImageView circleImgView;
	private TextView txtName, txtPhonenumber;

	

	private SlidingMenu mSlidingMenu;

	private int textColorAn,textColorLight;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_show);

		initView();
		/*
		 * mMenu = (SlidingLayout) findViewById(R.id.id_menu_layout);
		 * mMenu.setScrollEvent(findViewById(R.id.content));
		 */
		initSlidingMenu(savedInstanceState);

		initFragment(savedInstanceState);
		initUserInfo();
		new ActionBarBuilder(this).setLeftGone().setTitleText("帮帮堂");
		// 初始化默认图片
		//getDefaultImgHead();
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

	/**
	 * 初始化侧边栏
	 */
	private void initSlidingMenu(Bundle savedInstanceState) {

		// 如果保存的状态不为空则得到之前保存的Fragment，否则实例化MyFragment

		// 设置左侧滑动菜单
		setBehindContentView(R.layout.layout_menu);
		
		mSlidingMenu = getSlidingMenu();
		// 设置可以左右滑动的菜单
		mSlidingMenu.setMode(SlidingMenu.LEFT);
		// 设置滑动阴影的宽度
		mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		// 设置滑动菜单阴影的图像资源
		mSlidingMenu.setShadowDrawable(null);
		// 设置滑动菜单视图的宽度
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// 设置渐入渐出效果的值
		mSlidingMenu.setFadeDegree(0.35f);
		mSlidingMenu.setAnimation(null);
		// 设置触摸屏幕的模式,这里设置为全屏
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		// 设置下方视图的在滚动时的缩放比例
		mSlidingMenu.setBehindScrollScale(0.0f);

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
		Button btnShow = (Button) findViewById(R.id.id_btn_message);
		Button btnTime = (Button) findViewById(R.id.id_btn_doing);
		btns = new Button[] { btnHome, btnNews, btnShow, btnTime };
		circleImgView = (CircleImageView) findViewById(R.id.menu_circle_img);
		txtName = (TextView) findViewById(R.id.menu_info_name);
		txtPhonenumber = (TextView) findViewById(R.id.menu_info_phonenumber);
		
		txts = new TextView[4];
		txts[0] = (TextView) findViewById(R.id.id_txt_home);
		txts[1] = (TextView) findViewById(R.id.id_txt_show);
		txts[2] = (TextView) findViewById(R.id.id_txt_message);
		txts[3] = (TextView) findViewById(R.id.id_txt_doing);
		
		textColorAn = getResources().getColor(R.color.text_an);
		 textColorLight = getResources().getColor(R.color.text_light);
	}

	public void onMainClick(View v) {
		Log.d(TAG, "+++onMainClick");
		Intent intentTarget = new Intent();
		switch (v.getId()) {

		case R.id.MyOrder:
			intentTarget.setClass(this, Order_Activity.class);
			startActivity(intentTarget);
			break;
		case R.id.Purse:
			intentTarget.setClass(this, Purse_Activity.class);
			startActivity(intentTarget);
			break;
		case R.id.MyCredibility:
			intentTarget.setClass(this, Credibility_Activity.class);
			startActivity(intentTarget);
			break;
		case R.id.About:
			intentTarget.setClass(this, About_Activity.class);
			startActivity(intentTarget);
			break;
		case R.id.HistoryRecord:
			intentTarget.setClass(this, History_Activity.class);
			startActivity(intentTarget);
			break;
		case R.id.Set:
			intentTarget.setClass(this, Set_Activity.class);
			startActivity(intentTarget);
			break;
		case R.id.id_btn_home:
			changeFragment(0);
			break;
		case R.id.id_btn_show:
			changeFragment(1);
			break;
		case R.id.id_btn_message:
			changeFragment(2);
			break;
		case R.id.id_btn_doing:
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
		
		for(int i=0;i<4;i++){
			btns[i].setBackgroundResource(normalDrawable[i]);
			txts[i].setTextColor(textColorAn);
		}
		
		btns[which].setBackgroundResource(pressDrawable[which]);
        txts[which].setTextColor(textColorLight);		
		
		
	}

	private void initFragment(Bundle savedInstanceState) {
		
		
		if( savedInstanceState != null){
			
			fragments[0] = getSupportFragmentManager().findFragmentByTag(fragments[0].getClass().getName());
			fragments[1] = getSupportFragmentManager().findFragmentByTag(fragments[1].getClass().getName());
			fragments[2] = getSupportFragmentManager().findFragmentByTag(fragments[2].getClass().getName());
			fragments[3] = getSupportFragmentManager().findFragmentByTag(fragments[3].getClass().getName());
			
			currentFragment = savedInstanceState.getInt(KEY_INDEX);
			
			 getSupportFragmentManager().beginTransaction()
			 .hide(fragments[0]).hide(fragments[1]).hide(fragments[2]).hide(fragments[3])
			 .show(fragments[currentFragment]);
			
		}else{
			
			Bundle bundle = new Bundle();
			Fragment fragment1 = HomeFragment.newInstance();
			MainFragment fragment2 = new MainFragment(1);
			Fragment fragment3 = new PresentFragement();
			MainFragment fragment4 = new MainFragment(3);
			fragments = new Fragment[] { fragment1, fragment2, fragment3, fragment4 };
			getSupportFragmentManager().beginTransaction()
			.add(R.id.id_frame_layout, fragments[0],fragments[0].getClass().getName())
		    .add(R.id.id_frame_layout, fragments[1],fragments[1].getClass().getName())
		    .add(R.id.id_frame_layout, fragments[2],fragments[2].getClass().getName())
		    .add(R.id.id_frame_layout, fragments[3],fragments[3].getClass().getName())
		    .hide(fragments[0])
		    .hide(fragments[1])
		    .hide(fragments[2])
		    .hide(fragments[3])
			.show(fragments[0]).commit();
			
		}
	}
	
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(KEY_INDEX, currentFragment);
	}
	
	

	public void toggleMenu(View view) {

		mSlidingMenu.toggle(false);

		// mMenu.toggle();
		// mMenu.scrollToRightLayout();
	}

}
