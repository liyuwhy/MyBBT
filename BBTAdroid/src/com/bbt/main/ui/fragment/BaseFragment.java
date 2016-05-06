package com.bbt.main.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		findView();
		setListener();
		init();
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	/**
	 * �������
	 */
	protected abstract void findView();

	/**
	 * ���ü���
	 */
	protected abstract void setListener();

	/**
	 * ��ʼ���߼�
	 */
	protected abstract void init();

}
