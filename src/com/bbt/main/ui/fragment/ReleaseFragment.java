package com.bbt.main.ui.fragment;
import com.bbt.main.ui.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class ReleaseFragment extends Fragment{
	private Context context;
	
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		context=getActivity();
		view=inflater.inflate(R.layout.release, null);
		// TODO Auto-generated method stub
		return view;
	}

}
