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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bbt.main.ui.R;
import com.bbt.main.ui.Adapt.ListBaseAdapter;
import com.bbt.main.ui.Adapt.ViewPagerAdapter;

//import com.jiu.B1411.Student.Student_square;
public class HomeFragment extends BaseFragment implements View.OnClickListener {
	private ListBaseAdapter adapter1;
	private ListView listv;
	private Context context;
	private View mBaseView;
	private List<Map<String, Object>> list;
	private ViewPager mViewPaper;
	private List<ImageView> images;
	private List<View> dots;
	private int currentItem;
	// ��¼��һ�ε��λ��
	private int oldPosition = 0;
	// ���ͼƬ��id

	private int[] imageIds = new int[] { R.drawable.thumb_raw_1459858925, R.drawable.thumb_raw_1459859301,
			R.drawable.thumb_raw_1459860103 };
	private String names[] = new String[] { "����", "����", "������", "yoho" };
	private String time[] = new String[] { "21����ǰ", "41����ǰ", "1��Сʱǰ", "2��Сʱǰ" };
	private String descs[] = new String[] { "[����]��ݱ�ţ�*��*����ͨ��ݣ������ѧ��������30��ʱ�д��ֵ���ȡ", "��ͨ���", "С����ȡ���", "������š���ע΢�š���" };
	private int[] imageView = new int[] { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d };
	private int[] imageView1 = new int[] { R.drawable.nv, R.drawable.nan, R.drawable.nv, R.drawable.nv };
	private int[] imageView2 = new int[] { R.id.xian, R.id.xian, R.id.xian, R.id.xian };
	private int[] imageView3 = new int[] { R.id.xian2, R.id.xian2, R.id.xian2, R.id.xian2 };
	private String[] school = new String[] { "�й������ѧ", "�й������ѧ", "�й������ѧ", "�й������ѧ" };
	private int[] pao = new int[] { R.drawable.pao, R.drawable.pao, R.drawable.pao, R.drawable.pao };
	private String fabu[] = new String[] { "�·���", "������", "�·���", "�·���" };
	private String money[] = new String[] { "׬2Ԫ", "׬10Ԫ", "׬20Ԫ", "׬3Ԫ" };

	private TextView title1;
	private ViewPagerAdapter adapter;
	private ScheduledExecutorService scheduledExecutorService;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		context = getActivity();
		mBaseView = inflater.inflate(R.layout.fragment_home, null);
		super.onCreateView(inflater, container, savedInstanceState);
		setData();
		findView();
		adapter1 = new ListBaseAdapter(list);
		listv.setAdapter(adapter1);

		adapter = new ViewPagerAdapter(images);
		mViewPaper.setAdapter(adapter);
		return mBaseView;
	}

	private void setData() {
		// ʵ��������

		list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < names.length; i++) {
			// ������ֵ�Բ�ʵ����
			Map<String, Object> map = new HashMap<String, Object>();
			// �����ݴ����ֵ��
			// map.put("title", title[i]); // ����
			// map.put("name", names[i]); // ������
			// map.put("content", content[i]); // ����
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
			// ����ֵ�Դ��뼯��
			list.add(map);
		}
	}

	/**
	 * �����̳߳ض�ʱִ�ж����ֲ�
	 */
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleWithFixedDelay(new ViewPageTask(), 4, 3, TimeUnit.SECONDS);
	}

	private class ViewPageTask implements Runnable {

		@Override
		public void run() {
			currentItem = (currentItem + 1) % imageIds.length;
			mHandler.sendEmptyMessage(0);
		}
	}

	/**
	 * �������̴߳��ݹ���������
	 */
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mViewPaper.setCurrentItem(currentItem);
		};
	};

	@Override
	protected void findView() {
		mViewPaper = (ViewPager) mBaseView.findViewById(R.id.vp);
		listv = (ListView) mBaseView.findViewById(R.id.myfirstpager_list);

		// ��ʾ��С��
		dots = new ArrayList<View>();
		dots.add(mBaseView.findViewById(R.id.dot_0));
		dots.add(mBaseView.findViewById(R.id.dot_1));
		dots.add(mBaseView.findViewById(R.id.dot_2));
	}

	@Override
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

	@Override
	protected void init() {
		// ��ʾ��ͼƬ
		images = new ArrayList<ImageView>();
		for (int i = 0; i < imageIds.length; i++) {
			ImageView imageView = new ImageView(context);
			imageView.setBackgroundResource(imageIds[i]);
			images.add(imageView);
		}
		// ����ͼƬ����
		// title.setText(titles[0]);
	}

	@Override
	public void onClick(View view) {

		int Id = view.getId();
		FragmentManager fm;
		FragmentTransaction ft;
		Bundle bundle = new Bundle();
		Intent intent;

	}

}
