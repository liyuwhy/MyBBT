package com.bbt.main.base;

import com.bbt.main.inter.BaseViewInterface;
import com.bbt.main.ui.dialog.DialogControl;
import com.bbt.main.ui.dialog.WaitDialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public abstract class BaseActivity extends Activity implements DialogControl, View.OnClickListener, BaseViewInterface {

	private WaitDialog _waitDialog;

	
	protected abstract int getLayoutId();
	


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutId());
		initView();
		initData();
	}
	
	



	public WaitDialog showWaitDialog(String message) {
		if (_waitDialog == null) {
			_waitDialog = WaitDialog.getWaitDialog(this, message);
		}
		if (_waitDialog != null) {
			_waitDialog.setMessage(message);
			_waitDialog.show();
		}
		return _waitDialog;
	}

	@Override
	public void hideWaitDialog() {
		if (_waitDialog != null) {
			try {
				_waitDialog.dismiss();
				_waitDialog = null;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public WaitDialog showWaitDialog() {
		return showWaitDialog("正在加载...");
	}

	@Override
	public WaitDialog showWaitDialog(int resid) {
		return showWaitDialog(getString(resid));
	}

	
	public void startActivity(Class<?> cls){
		Intent intent = new Intent();
		intent.setClass(this, cls);
		startActivity(intent);
	}
	
	
}
