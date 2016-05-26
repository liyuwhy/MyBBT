package com.bbt.main.ui;

import com.bbt.main.tool.ActionBarBuilder;

import android.app.Activity;
import android.os.Bundle;

public class OrderdetailsActivity extends Activity{
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.order_details);
	System.out.println("details");
	new ActionBarBuilder(this).setTitleText("订单详情");
	
	
}
}
