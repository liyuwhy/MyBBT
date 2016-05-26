package com.bbt.main.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import com.bbt.main.base.BaseFragment;
import com.bbt.main.base.ListBaseAdapter;
import com.bbt.main.ui.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class PreAcceptFragment extends BaseFragment
		implements SwipeRefreshLayout.OnRefreshListener, OnItemClickListener, OnScrollListener {

	public static final int STATE_NONE = 0;
	public static final int STATE_REFRESH = 1;
	public static final int STATE_LOADMORE = 2;
	public static final int STATE_NOMORE = 3;
	public static final int STATE_PRESSNONE = 4;// 正在下拉但还没有到刷新的状态
	public static int mState = STATE_NONE;

	private ListView mListView;

	protected SwipeRefreshLayout mSwipeRefreshLayout;
	private int mCurrentPage;
	ArrayList<String> myData;

	{
		myData = new ArrayList<String>();
		myData.add("show");
		myData.add("show");
		myData.add("show");
	}

	@Override
	protected void initView(View view, Bundle saveInstanceState) {
		mListView = (ListView) view.findViewById(R.id.id_accept_listview);
		mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);
		mSwipeRefreshLayout.setColorScheme(R.color.swiperefresh_color1, R.color.swiperefresh_color2,
				R.color.swiperefresh_color3, R.color.swiperefresh_color4);

		// 显示正在加载

		mListView.setOnItemClickListener(this);
		mListView.setOnScrollListener(this);

		mSwipeRefreshLayout.setOnRefreshListener(this);

		if (mAdapter != null) {
			mAdapter.setState(ListBaseAdapter.STATE_LOAD_MORE);
			mListView.setAdapter(mAdapter);
			mAdapter.setData(myData);
		}
		mState = STATE_NONE;
		requestData();
	}

	@Override
	public void onResume() {
		super.onResume();
		// TODO 根据一定条件判断是否需要刷新
		onRefresh();
	}

	private void requestData() {

		if (mState == STATE_LOADMORE) {
			// 下拉加载更多

			mAdapter.setState(ListBaseAdapter.STATE_LOAD_MORE);

			mListView.postDelayed(new Runnable() {

				@Override
				public void run() {
					List<String> newData = new ArrayList<String>();
					newData.add("SS");
					newData.add("BB");
					executeOnLoadDataSuccess(newData);
					mState = STATE_NONE;
				}
			}, 1000 * 5);

		} else {
			// TODO Auto-generated method stub
			// 加载数据
			// 加载完毕后 调用 set
			mSwipeRefreshLayout.postDelayed(new Runnable() {

				@Override
				public void run() {
					executeOnLoadFinish();
				}
			}, 1000 * 5);

		}
	}

	@Override
	protected int getLayoutId() {
		return R.layout.accept;
	}

	@Override
	public void onRefresh() {
		if (mState == STATE_REFRESH) {
			return;
		}
		// 设置顶部正在刷新
		mListView.setSelection(0);
		setSwipeRefreshLoadingState();
		mCurrentPage = 0;
		mState = STATE_REFRESH;
		requestData();
	}

	private void setSwipeRefreshLoadingState() {
		if (mSwipeRefreshLayout != null) {
			mSwipeRefreshLayout.setRefreshing(true);
			// 防止多次重复刷新
			mSwipeRefreshLayout.setEnabled(false);
		}

	}

	/** 设置顶部加载完毕的状态 */
	private void setSwipeRefreshLoadedState() {
		if (mSwipeRefreshLayout != null) {
			mSwipeRefreshLayout.setRefreshing(false);
			mSwipeRefreshLayout.setEnabled(true);
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

	}

	// 下拉加载更多
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

		Log.d("TAG", "onScrollStateChanged");
		if (mAdapter == null || mAdapter.getCount() == 0) {
			return;
		}
		// 数据已经全部加载，或数据为空时，或正在加载，不处理滚动事件
		if (mState == STATE_LOADMORE || mState == STATE_REFRESH) {
			return;
		}
		// 判断是否滚动到底部
		boolean scrollEnd = false;
		try {
			if (view.getPositionForView(mAdapter.getFooterView()) == view.getLastVisiblePosition())
				scrollEnd = true;
			Log.d("TAG", "onScrollStateChanged to scrollEnd");
		} catch (Exception e) {
			scrollEnd = false;
		}

		if (mState == STATE_NONE && scrollEnd) {

			Log.d("TAG", "加载更多");

			if (mAdapter.getState() == ListBaseAdapter.STATE_LOAD_MORE
					|| mAdapter.getState() == ListBaseAdapter.STATE_NETWORK_ERROR) {
				mCurrentPage++;
				mState = STATE_LOADMORE;

				requestData();

				mAdapter.setFooterViewLoading();
			}
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	protected void executeOnLoadDataSuccess(List<String> data) {
		if (data == null) {
			data = new ArrayList<String>();
		}

		/*
		 * if (mResult != null && !mResult.OK()) {
		 * AppContext.showToast(mResult.getErrorMessage()); //
		 * 注销登陆，密码已经修改，cookie，失效了 AppContext.getInstance().Logout(); }
		 */
		// mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
		if (mCurrentPage == 0) {
			mAdapter.clear();
		}
		/*
		 * 判断和原先数据是否相同 for (int i = 0; i < data.size(); i++) { if
		 * (compareTo(mAdapter.getData(), data.get(i))) { data.remove(i); i--; }
		 * }
		 */
		int adapterState = ListBaseAdapter.STATE_EMPTY_ITEM;
		if ((mAdapter.getCount() + data.size()) == 0) {
			adapterState = ListBaseAdapter.STATE_EMPTY_ITEM;
		} else if (data.size() == 0 || (data.size() < getPageSize() && mCurrentPage == 0)) {
			adapterState = ListBaseAdapter.STATE_NO_MORE;
			mAdapter.notifyDataSetChanged();
		} else {
			adapterState = ListBaseAdapter.STATE_LOAD_MORE;
		}
		mAdapter.setState(adapterState);
		mAdapter.addData(data);
		// 判断等于是因为最后有一项是listview的状态
		if (mAdapter.getCount() == 1) {

			mAdapter.setState(ListBaseAdapter.STATE_EMPTY_ITEM);
			mAdapter.notifyDataSetChanged();

		}
	}

	private int getPageSize() {
		// TODO Auto-generated method stub
		return 3;
	}

	// 完成刷新
	protected void executeOnLoadFinish() {
		setSwipeRefreshLoadedState();
		mState = STATE_NONE;
	}

	private com.bbt.main.base.ListBaseAdapter<String> mAdapter = new com.bbt.main.base.ListBaseAdapter<String>() {

		protected View getRealView(int position, View convertView, ViewGroup parent) {
			View view = getHoldingActivity().getLayoutInflater().inflate(R.layout.item_doing_get, null, false);
			return view;

		};

	};

}
