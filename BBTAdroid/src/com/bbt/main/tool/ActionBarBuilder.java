package com.bbt.main.tool;

import com.bbt.main.ui.R;

import android.accounts.Account;
import android.accounts.OnAccountsUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ActionBarBuilder {
	
	private Activity  mActivity ;
	private ImageView leftImg;
	private TextView centerTxt;
	
	
	public ActionBarBuilder(Activity activity){
		mActivity = activity;
		
		leftImg = (ImageView)mActivity.findViewById(R.id.title_left_img);
		if( null == leftImg){
			throw new RuntimeException("please inflate the title layout first");
		}
	    leftImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
                  mActivity.finish();				
			}
		});	
		centerTxt = (TextView)mActivity.findViewById(R.id.title_center_txt);
	}
	
	
	
	public ActionBarBuilder setLeftGone(){
		leftImg.setVisibility(View.GONE);
		return this;
	}
	
	public ActionBarBuilder setTitleText(String title){
		centerTxt.setText(title);
		return this;
	}
	

}
