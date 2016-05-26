package com.bbt.main.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.Header;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.bbt.main.base.BBTApp;
import com.bbt.main.base.BaseActivity;
import com.bbt.main.bean.OrderCase;
import com.bbt.main.net.BBTApi;
import com.bbt.main.tool.ActionBarBuilder;
import com.bbt.main.tool.SPUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class OrderActivity extends BaseActivity {

	public static final String KEY_ORDER_TYPE = "order_type";

	Button submitBtn;
	EditText contentEdit, addressEdit, moneyEdit;
	TextView dataTxt, timeTxt, nameTxt, phoneTxt;

	private final static int DATE_DIALOG = 0;
	private final static int TIME_DIALOG = 1;
	private Calendar c = null;
	private String type;
	Date date = new Date();

	private EditText extraEdit;

	private AsyncHttpResponseHandler hander = new AsyncHttpResponseHandler() {

		@Override
		public void onSuccess(int arg0, Header[] arg1, byte[] data) {
			hideWaitDialog();
			Toast.makeText(OrderActivity.this, "成功" + new String(data), 5 * 1000).show();
			startActivity(MainActivity.class);
		}

		@Override
		public void onFailure(int arg0, Header[] arg1, byte[] data, Throwable arg3) {
			hideWaitDialog();
			Toast.makeText(OrderActivity.this, "失败" , 5 * 1000).show();
		}
	};

	/**
	 * 创建日期及时间选择对话框
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch (id) {
		case DATE_DIALOG:
			c = Calendar.getInstance();
			dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
				public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {
					dataTxt.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
				}
			}, c.get(Calendar.YEAR), // 传入年份
					c.get(Calendar.MONTH), // 传入月份
					c.get(Calendar.DAY_OF_MONTH) // 传入天数
			);
			break;
		case TIME_DIALOG:
			c = Calendar.getInstance();
			dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					timeTxt.setText(hourOfDay + ":" + minute);
				}
			}, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false);
			break;
		}
		return dialog;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.order_txt_date:
			showDialog(DATE_DIALOG);
			break;
		case R.id.order_txt_time:
			showDialog(TIME_DIALOG);
			break;
		case R.id.order_btn_submit:

			String contentStr = contentEdit.getText().toString();
			String addressStr = addressEdit.getText().toString();
			float moneyStr = Float.valueOf(moneyEdit.getText().toString());
			String timeStr = dataTxt.getText().toString() + " " + timeTxt.getText().toString();

			RequestParams params = new RequestParams();
			params.put("order_content", contentStr);
			params.put("sender_addr", addressStr);
			params.put("pay", moneyStr);
			params.put("finishTime", timeStr);
			
			Log.d("TAG", "userId"+new SPUtil(this).getUserId());
			params.put("userId", new SPUtil(this).getUserId());
			params.put("extra_need", "额外快递");
			params.put("type", type);
			params.put("pay_type", "offline");
			showWaitDialog();

			BBTApi.sendOrder(params, hander);
			// postOrder();
			break;

		default:
			break;
		}

	}

	@Override
	public void initView() {
		submitBtn = (Button) findViewById(R.id.order_btn_submit);
		contentEdit = (EditText) findViewById(R.id.order_edit_content);
		addressEdit = (EditText) findViewById(R.id.order_edit_address);

		extraEdit = (EditText) findViewById(R.id.order_edit_extra_need);

		moneyEdit = (EditText) findViewById(R.id.order_edit_money);
		dataTxt = (TextView) findViewById(R.id.order_txt_date);
		timeTxt = (TextView) findViewById(R.id.order_txt_time);
		nameTxt = (TextView) findViewById(R.id.order_txt_name);
		phoneTxt = (TextView) findViewById(R.id.order_txt_phone);

		// 初始化 时间

		SimpleDateFormat formatDate = new SimpleDateFormat("yy-MM-dd");
		SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
		dataTxt.setText(formatDate.format(date));
		timeTxt.setText(formatTime.format(date));

		// 初始化 姓名 电话
		SPUtil spUtil = new SPUtil(this);
		nameTxt.setText(spUtil.getUserName());
		phoneTxt.setText(spUtil.getPhoneNumber());

	}

	@Override
	public void initData() {
		new ActionBarBuilder(this).setTitleText("发布订单");
		
		
		type = getIntent().getStringExtra(KEY_ORDER_TYPE);

	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_order;
	}

}
