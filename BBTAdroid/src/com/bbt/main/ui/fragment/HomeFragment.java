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
import com.bbt.main.ui.OrderdetailsActivity;
import com.bbt.main.ui.R;
import com.bbt.main.ui.Adapt.ListBaseAdapter;
import com.bbt.main.ui.Adapt.ViewPagerAdapter;
public class HomeFragment extends BaseFragment implements OnItemClickListener {
	
	
	
	
	public static HomeFragment newInstance(){
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
	private int[] imageIds = new int[] { R.drawable.ad_top_c, R.drawable.ad_top_o,
			R.drawable.ad_top_t };
	private String names[] = new String[] { "珠珠", "林茜", "李欣书", "yoho" };
	private String time[] = new String[] { "21分钟前", "41分钟前", "1个小时前", "2个小时前" };
	private String descs[] = new String[] { "[峰达达]快递编号（*码*）中通快递，民族大学西门右走30米时尚丛林店内取", "中通快递", "小东门取快递", "【快递信】关注微信。。" };
	private int[] imageView = new int[] { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d };
	private int[] imageView1 = new int[] { R.drawable.nv, R.drawable.nan, R.drawable.nv, R.drawable.nv };
	private int[] imageView2 = new int[] { R.id.xian, R.id.xian, R.id.xian, R.id.xian };
	private int[] imageView3 = new int[] { R.id.xian2, R.id.xian2, R.id.xian2, R.id.xian2 };
	private String[] school = new String[] { "中国民族大学", "中国民族大学", "中国民族大学", "中国民族大学" };
	private int[] pao = new int[] { R.drawable.pao, R.drawable.pao, R.drawable.pao, R.drawable.pao };
	private String fabu[] = new String[] { "新发布", "被抢了", "新发布", "新发布" };
	private String money[] = new String[] { "赚2元", "赚10元", "赚20元", "赚3元" };
	private TextView title1;
	private ViewPagerAdapter adapter;
	private ScheduledExecutorService scheduledExecutorService;
	
	

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
	/*	scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleWithFixedDelay(new ViewPageTask(), 4, 3, TimeUnit.SECONDS);
		
		*/
		
		initData();
		listv = (ListView) view.findViewById(R.id.myfirstpager_list);
		adapter1 = new ListBaseAdapter(list);
		listv.setAdapter(adapter1);
		listv.setOnItemClickListener(this);
		
	}
	
	private void initData() {
		// 实例化集合
		list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < names.length; i++) {
			// 创建键值对并实例化
			Map<String, Object> map = new HashMap<String, Object>();
			// 将数据存入键值对
			// map.put("title", title[i]);  标题
			// map.put("name", names[i]);  发布者
			// map.put("content", content[i]);  内容
			map.put("head", imageView[i]);
			map.put("personName", names[i]);
			map.put("time", time[i]);
			map.put("image", imageView1[i]);
			map.put("desc", descs[i]);
			map.put("xian", imageView2[i]);
			map.put("school", school[i]);
			map.put("pao", pao[i]);
			map.put("fabu", fabu[i]);
			map.put("imageView", imageView3[i]);
			map.put("money", money[i]);
			// 将键值对存入集合
			list.add(map);
		}
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
		// TODO Auto-generated method stub
		int imageView=(Integer) list.get(position).get("head");
		String name=list.get(position).get("personName").toString();
		String time=list.get(position).get("time").toString();
		int imageView1=(Integer) list.get(position).get("image");
		String descs=list.get(position).get("desc").toString();
		int imageView2=(Integer) list.get(position).get("xian");
		String school=list.get(position).get("school").toString();
		int pao=(Integer) list.get(position).get("pao");
		String fabu=list.get(position).get("fabu").toString();
		int imageView3=(Integer) list.get(position).get("imageView");
		String money=list.get(position).get("money").toString();
		
		Intent intent=new Intent(getActivity(),OrderdetailsActivity.class);
		Bundle bundle=new Bundle();
		bundle.putInt("head",imageView);
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
		intent.putExtras(bundle);
		startActivity(intent);
		
	}
}
