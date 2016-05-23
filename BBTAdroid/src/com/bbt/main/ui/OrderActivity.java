package com.bbt.main.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.bbt.main.base.BBTApp;
import com.bbt.main.bean.OrderCase;
import com.bbt.main.tool.ActionBarBuilder;
import com.bbt.main.tool.SPUtil;
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

public class OrderActivity extends Activity {

	
	public static final String KEY_ORDER_TYPE = "order_type";
	
	
	
	Button submitBtn;
	EditText contentEdit, addressEdit, moneyEdit;
	TextView dataTxt, timeTxt, nameTxt, phoneTxt;

	private final static int DATE_DIALOG = 0;
	private final static int TIME_DIALOG = 1;
	private Calendar c = null;

	Date date = new Date();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		initView();
		new ActionBarBuilder(this).setTitleText("��������");
		
		String type = getIntent().getStringExtra(KEY_ORDER_TYPE);
	}

	@SuppressLint("SimpleDateFormat")
	private void initView() {
		submitBtn = (Button) findViewById(R.id.order_btn_submit);
		contentEdit = (EditText) findViewById(R.id.order_edit_content);
		addressEdit = (EditText) findViewById(R.id.order_edit_address);
		moneyEdit = (EditText) findViewById(R.id.order_edit_money);
		dataTxt = (TextView) findViewById(R.id.order_txt_date);
		timeTxt = (TextView) findViewById(R.id.order_txt_time);
		nameTxt = (TextView) findViewById(R.id.order_txt_name);
		phoneTxt = (TextView) findViewById(R.id.order_txt_phone);

		// ��ʼ�� ʱ��

		SimpleDateFormat formatDate = new SimpleDateFormat("yy-MM-dd");
		SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
		dataTxt.setText(formatDate.format(date));
		timeTxt.setText(formatTime.format(date));

		// ��ʼ�� ���� �绰
		SPUtil spUtil = new SPUtil(this);
		nameTxt.setText(spUtil.getUserName());
		phoneTxt.setText(spUtil.getPhoneNumber());
	}

	public void onMainClick(View v) {

		switch (v.getId()) {
		case R.id.order_txt_date:
			showDialog(DATE_DIALOG);
			break;
		case R.id.order_txt_time:
			showDialog(TIME_DIALOG);
			break;
		case R.id.order_btn_submit:
			//postOrder();
			break;

		default:
			break;
		}

	}

/*	private void postOrder() {
		
		AVUser user = ((BBTApp)getApplication()).getAvUser();
		if ( user == null) {
			// δ��¼
			Toast.makeText(this, "δ��¼", 3 * 1000).show();
			return;
		}

		String contentStr = contentEdit.getText().toString();
		String addressStr = addressEdit.getText().toString();
		float moneyStr = Float.valueOf(moneyEdit.getText().toString());
		String timeStr = dataTxt.getText().toString() + " " + timeTxt.getText().toString();
		OrderCase order = new OrderCase(contentStr, addressStr, timeStr, moneyStr);
	
	
		Log.d("Order", "order"+order.getObjectId());
	 
		OrderCaseDao.postOrder( user, order, new SaveCallback() {

			@Override
			public void done(AVException e) {
				if( e== null){
					Toast.makeText(OrderActivity.this, "�ύ�ɹ�", 5 * 1000).show();
				}else{
					Toast.makeText(OrderActivity.this, "�ύʧ��"+e.getMessage(), 5 * 1000).show();
				}
				
			}
		});

	}*/

	/**
	 * �������ڼ�ʱ��ѡ��Ի���
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
			}, c.get(Calendar.YEAR), // �������
					c.get(Calendar.MONTH), // �����·�
					c.get(Calendar.DAY_OF_MONTH) // ��������
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

}
