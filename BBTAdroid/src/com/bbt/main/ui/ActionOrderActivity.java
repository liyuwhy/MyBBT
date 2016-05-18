package com.bbt.main.ui;

import com.avos.avoscloud.LogUtil.log;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActionOrderActivity extends Activity {

	public static final String TAG = "ActionOrderActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_order);
	}

	public void onCancelClick(View v) {
		log.d(TAG, "onOrderClick");
		finish();
		overridePendingTransition(R.anim.no_push, R.anim.bottom_to_top);
	}

	public void onOrderBtn(View v) {
		log.d(TAG, "onOrderClick btn");
		switch (v.getId()) {
		case R.id.order_action_post:
			Intent intent = new Intent(this, OrderActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
