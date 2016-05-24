package com.bbt.main.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.bbt.main.ui.R;
import com.bbt.main.view.CircleImageView;

public class PresentFragement extends Fragment {
	private Context context;
	private View view;
	private Fragment[] fragments;
	private TextView release, accpect;

	
	private OnClickListener mOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			FragmentTransaction transaction = getFragmentManager().beginTransaction();
			
			if(v.getId() == R.id.accpect){
				 transaction.hide(fragments[0]).show(fragments[1]).commit();
				 accpect.setTextSize(30);
				 relase.setTextSize(25);
			}else if( v.getId() == R.id.relase){
				 transaction.hide(fragments[1]).show(fragments[0]).commit();
				 accpect.setTextSize(25);
				 relase.setTextSize(30);
			}
			
		}
	};
	private TextView relase;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		context = getActivity();
		view = inflater.inflate(R.layout.fragemtn_present_main, null);
		accpect = (TextView) view.findViewById(R.id.accpect);
 		relase = (TextView)view.findViewById(R.id.relase);
 		
 		accpect.setOnClickListener(mOnClickListener);
 		relase.setOnClickListener(mOnClickListener);
 		
		initFragment();
		// TODO Auto-generated method stub
		return view;
	}

	private void changeFragment() {

	}

	private void initFragment() {
		Bundle bundle = new Bundle();
		Fragment fragment1 = new PreReleaseFragment();
		Fragment fragment2 = new PreAcceptFragment();
		fragments = new Fragment[] { fragment1, fragment2 };
		getFragmentManager().beginTransaction().add(R.id.present_frame_layout, fragment1)
				.add(R.id.present_frame_layout, fragment2).hide(fragment1).hide(fragment2).show(fragment2).commit();
	}
}
