package com.bbt.main.ui.fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.bbt.main.ui.R;
import com.bbt.main.view.CircleImageView;
public class PresentFragement extends Fragment{
private Context context;
private View view;
private Fragment[] fragments;
private Button release,accpect;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		context=getActivity();
		view=inflater.inflate(R.layout.present1,null);
		accpect=(Button) view.findViewById(R.id.accpect);
		initFragment();
		// TODO Auto-generated method stub
		return view;
	}
	private void initFragment() {
		Bundle bundle = new Bundle();
		Fragment fragment1 = new ReleaseFragment();
		Fragment fragment2 = new AcceptFragment();
		fragments = new Fragment[] { fragment1, fragment2};
		getFragmentManager().beginTransaction().add(R.id.present1_frame_layout, fragment1)
				.add(R.id.present1_frame_layout, fragment2).hide(fragment1)
				.hide(fragment2)
				.show(fragment2).commit();
	}
}
