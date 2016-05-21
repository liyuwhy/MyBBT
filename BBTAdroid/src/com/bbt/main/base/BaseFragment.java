package com.bbt.main.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
	
	
	protected Activity mActivity;
	
	protected abstract void initView(View view,Bundle saveInstanceState);
	
	protected abstract int getLayoutId();
	
	protected Activity getHoldingActivity() {
		return mActivity;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = activity;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(getLayoutId(), container,false);
	    initView(view, savedInstanceState);
		return view;
	}


}
