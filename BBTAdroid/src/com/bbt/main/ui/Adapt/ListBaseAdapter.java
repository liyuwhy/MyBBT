package com.bbt.main.ui.Adapt;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Text;

import com.bbt.main.net.BBTApi;
import com.bbt.main.tool.StringUtils;
import com.bbt.main.ui.R;
import com.bbt.main.ui.Adapt.ListBaseAdapter.ViewHolder;
import com.bbt.main.view.CircleImageView;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
               

public class ListBaseAdapter extends BaseAdapter{
	private List<Map<String, Object>> list1;
	private ViewHolder holder;

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list1.size();
	}
	@Override	
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list1.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// ��һ�������б�
		if (convertView == null) {
			// ����View�����б���item��
			convertView = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.item_main_layout, parent, false);
			// ͨ��ViewHolder��ȥ��ȡ�ؼ���Ȼ��ViewHolder�����convertView��
			holder = new ViewHolder();
			holder.layout = (LinearLayout) convertView.findViewById(R.id.lineLayout);
			holder.address = (TextView) convertView.findViewById(R.id.tv_order_address);
			holder.time = (TextView) convertView.findViewById(R.id.tv_time);
			holder.context = (TextView) convertView.findViewById(R.id.tv_order_context);
			holder.nickname = (TextView) convertView.findViewById(R.id.tv_order_name);
			holder.order_money = (TextView) convertView.findViewById(R.id.tv_money);
			holder.icon=(CircleImageView) convertView.findViewById(R.id.menu_circle_img);
			// ��ViewHolder����
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		setData(position, parent);
		return convertView;
	}
	private void setData(int position, ViewGroup parent) {
		
		if(position%2==0){
			holder.layout.setBackgroundResource(R.drawable.item_main_bg);
		}else{
			holder.layout.setBackgroundResource(R.drawable.item_main_bg_change);
		}
		
		
		System.out.println("content"+list1.get(position).get("order_content").toString());
		System.out.println("list"+list1.get(position).get("happentime").toString());
		String friendlyText = StringUtils.friendly_time(list1.get(position).get("happentime").toString());
		holder.time.setText(friendlyText);
		
		
		
		
		holder.address.setText(list1.get(position).get("address").toString());
		holder.context.setText(list1.get(position).get("order_content").toString());
		holder.order_money.setText(list1.get(position).get("pay").toString());
		holder.nickname.setText(list1.get(position).get("name").toString());
		// ��ȡͼƬurl
		String image_url = list1.get(position).get("icon").toString();
		// ��ͼƬ�ؼ�����ֹͼƬ��λ
		holder.icon.setTag(image_url);
		
		BBTApi.displayHeadImg(holder.icon, image_url);
		
	}
	public class ViewHolder {
		LinearLayout layout;
		TextView nickname;
		TextView time;
		TextView address;
		TextView context;
		TextView order_money;
		CircleImageView icon;
	}
	public void setNewData(List<Map<String, Object>> list1) {
		// TODO Auto-generated method stub
		this.list1 = list1;
		// ֪ͨ����������
		notifyDataSetChanged();
	}
}
