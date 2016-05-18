package com.bbt.main.tool;

import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenUtils {

	public static int getScreenWidth(Context context) {
		DisplayMetrics metric = new DisplayMetrics();
		metric = context.getApplicationContext().getResources().getDisplayMetrics();
		return metric.widthPixels;  
	}
}
