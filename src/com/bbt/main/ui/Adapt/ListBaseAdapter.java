package com.bbt.main.ui.Adapt;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Text;

import com.bbt.main.ui.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
               

public class ListBaseAdapter extends BaseAdapter{
	List<Map<String, Object>> list;
	public ListBaseAdapter(List<Map<String, Object>> list) {
		this.list = list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
		
	}

	@Override	
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// 创建View
		view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,
				parent, false);
		// 配置数据
		setData(position, view);
		return view;
	}
	private void setData(int position, View view) {
		// TODO Auto-generated method stub
//		ImageView head =(ImageView) view.findViewById(R.id.head);
//		TextView name = (TextView) view.findViewById(R.id.name);
//		TextView time=(TextView) view.findViewById(R.id.time);
//		TextView descs=(TextView) view.findViewById(R.id.desc);
//		ImageView xian=(ImageView) view.findViewById(R.id.xian);
//		TextView school=(TextView) view.findViewById(R.id.school);
//		ImageView pao=(ImageView) view.findViewById(R.id.pao);
//		TextView fabu=(TextView) view.findViewById(R.id.fabu);
//		TextView money=(TextView) view.findViewById(R.id.money);
		// 为控件去配置数据
//		 map.put("head",imageView[i]);
//         map.put("personName",names[i]);
//         map.put("time",time[i]);
//         map.put("image",imageView1[i]);
//         map.put("desc",descs[i]);
//         map.put("xian",imageView2[i]);
//         map.put("school",school[i]);
//         map.put("pao",pao[i]);
//         map.put("fabu",fabu[i]);
//         map.put("imageView",imageView3[i]);
//         map.put("money",money[i]);
		Map<String,Object> data = (Map) getItem(position);
//		head.setImageResource((Integer) data.get("head"));
//		name.setText(list.get(position).get("personName").toString());
//		time.setText(list.get(position).get("time").toString());
//		descs.setText(list.get(position).get("desc").toString());
//		 xian.setImageResource((Integer) list.get(position).get("xian"));
//		 school.setText(list.get(position).get("school").toString());
//		 pao.setImageResource((Integer) list.get(position).get("pao"));
//		 fabu.setText(list.get(position).get("fabu").toString());
//		 money.setText(list.get(position).get("money").toString());
	}
}
