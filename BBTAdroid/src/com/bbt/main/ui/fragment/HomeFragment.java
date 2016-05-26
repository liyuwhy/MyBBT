package com.bbt.main.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceActivity.Header;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bbt.main.base.BaseFragment;
import com.bbt.main.net.BBTApi;
import com.bbt.main.tool.JsonHelper;
import com.bbt.main.ui.OrderdetailsActivity;
import com.bbt.main.ui.R;
import com.bbt.main.ui.Adapt.ListBaseAdapter;
import com.bbt.main.ui.Adapt.ViewPagerAdapter;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class HomeFragment extends BaseFragment implements OnItemClickListener {

	public static HomeFragment newInstance() {
		return new HomeFragment();
	}

	private ListBaseAdapter adapter1;
	private ListView listv;

	private List<Map<String, Object>> list;
	private ViewPager mViewPaper;
	private List<ImageView> images;
	private List<View> dots;
	private int currentItem;
	// 记录上一次点的位置
	private int oldPosition = 0;
	// 存放图片的id
	private int[] imageIds = new int[] { R.drawable.ad_top_c, R.drawable.ad_top_o, R.drawable.ad_top_t };

	private TextView title1;
	private ViewPagerAdapter adapter;
	private ScheduledExecutorService scheduledExecutorService;

	AsyncHttpResponseHandler hander = new AsyncHttpResponseHandler() {

		@Override
		public void onSuccess(int arg0, org.apache.http.Header[] arg1, byte[] result) {

			String jsonString = new String(result);
			System.out.println("jsonstring" + jsonString);
			
			
			// ����Json�ַ���
			list = JsonHelper.jsonStringToList(jsonString,
					new String[] { "address", "happentime", "order_content", "pay", "icon", "name" }, "data");
			adapter1.setNewData(list);
			listv.setAdapter(adapter1);
		}

		@Override
		public void onFailure(int arg0, org.apache.http.Header[] arg1, byte[] arg2, Throwable arg3) {
			// TODO Auto-generated method stub

		}
	};

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_home;
	}

	@Override
	protected void initView(View view, Bundle saveInstanceState) {

		mViewPaper = (ViewPager) view.findViewById(R.id.vp);
		// 显示的小点
		dots = new ArrayList<View>();
		dots.add(view.findViewById(R.id.dot_0));
		dots.add(view.findViewById(R.id.dot_1));
		dots.add(view.findViewById(R.id.dot_2));

		// 初始化 imgs
		init();
		adapter = new ViewPagerAdapter(images);
		mViewPaper.setAdapter(adapter);
		setListener();
		/*
		 * scheduledExecutorService =
		 * Executors.newSingleThreadScheduledExecutor();
		 * scheduledExecutorService.scheduleWithFixedDelay(new ViewPageTask(),
		 * 4, 3, TimeUnit.SECONDS);
		 * 
		 */

		initData();
		listv = (ListView) view.findViewById(R.id.myfirstpager_list);
		adapter1 = new ListBaseAdapter();
	
		listv.setOnItemClickListener(this);

	}

	private void initData() {
		BBTApi.getDefaultOrder(hander);
	}

	/**
	 * 利用线程池定时执行动画轮播
	 */

	private class ViewPageTask implements Runnable {

		@Override
		public void run() {
			currentItem = (currentItem + 1) % imageIds.length;
			mHandler.sendEmptyMessage(0);
		}
	}

	/**
	 * 接收子线程传递过来的数据
	 */
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mViewPaper.setCurrentItem(currentItem);
		};
	};

	protected void setListener() {

		// IV_title_stuSquarebtn_left.setOnClickListener(this);
		mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// title.setText(titles[position]);
				dots.get(position).setBackgroundResource(R.drawable.dot_focused);
				dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
				oldPosition = position;
				currentItem = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	protected void init() {
		// 显示的图片
		images = new ArrayList<ImageView>();
		for (int i = 0; i < imageIds.length; i++) {
			ImageView imageView = new ImageView(getHoldingActivity());
			imageView.setBackgroundResource(imageIds[i]);
			images.add(imageView);
		}
		// 设置图片标题
		// title.setText(titles[0]);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

		Intent intent = new Intent(getActivity(), OrderdetailsActivity.class);
		/*Bundle bundle = new Bundle();
		bundle.putInt("head", imageView);
		bundle.putString("personName", name);
		bundle.putString("time", time);
		bundle.putInt("image", imageView1);
		bundle.putString("desc", descs);
		bundle.putInt("xian", imageView2);
		bundle.putString("school", school);
		bundle.putInt("pao", pao);
		bundle.putString("fabu", fabu);
		bundle.putInt("imageView", imageView3);
		bundle.putString("money", money);
		intent.putExtras(bundle);*/
		startActivity(intent);

	}
}
