package com.bbt.main.ui.fragment;

import com.bbt.main.ui.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class PreAcceptFragment extends Fragment{
	private Context context;
	
	private View view;

	private ListView mListView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		context=getActivity();
		view=inflater.inflate(R.layout.accept, null);
		
		mListView = (ListView)view.findViewById(R.id.id_accept_listview);
		
		mListView.setAdapter(mAdapter);
		
		return view;
	}

	
	private BaseAdapter mAdapter = new BaseAdapter() {
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view=  getActivity().getLayoutInflater().inflate(R.layout.item_doing_get, null	, false);
			return view;
		}
		
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}
	};
}
