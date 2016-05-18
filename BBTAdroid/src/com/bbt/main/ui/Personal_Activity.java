package com.bbt.main.ui;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Personal_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_);
		TextView MyOrder=(TextView)findViewById(R.id.MyOrder);
		TextView MyPurse=(TextView)findViewById(R.id.Purse);
		
		TextView MyCredibility=(TextView)findViewById(R.id.MyCredibility);
		TextView MyAbout=(TextView)findViewById(R.id.About);
		TextView History=(TextView)findViewById(R.id.HistoryRecord);
		TextView MySet=(TextView)findViewById(R.id.Set);
		
		MyOrder.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Personal_Activity.this,Order_Activity.class);
				startActivity(i);
			}
		});
		
		MyPurse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i=new Intent(Personal_Activity.this,Purse_Activity.class);
				startActivity(i);
			}
		});
		
	
        MyCredibility.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i=new Intent(Personal_Activity.this,Credibility_Activity.class);
				startActivity(i);
			}
		});
        
        
        
        MyAbout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i=new Intent(Personal_Activity.this,About_Activity.class);
				startActivity(i);
			}
		});
        
        
        History.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i=new Intent(Personal_Activity.this,History_Activity.class);
				startActivity(i);
			}
		});
        
        
        MySet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i=new Intent(Personal_Activity.this,Set_Activity.class);
				startActivity(i);
			}
		});
	   
		
	 
	}
		
		
		

	
	

}
