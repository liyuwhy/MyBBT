package com.bbt.main.ui.fragment;

import com.bbt.main.ui.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment extends Fragment{
	public static final String TAG = "MainFragment";
	private int which;
	private String[] strInfo = {"首页","帮帮秀","进行中","消息"};
	public MainFragment(int i) {
		which = i;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_layout_main, container,false);
		TextView txtView = (TextView) view.findViewById(R.id.id_txtview);
		String str = strInfo[which] +"界面";
		Log.d(TAG, "+++getArgument"+str);
		txtView.setText(str);
		return view;
	}
}
