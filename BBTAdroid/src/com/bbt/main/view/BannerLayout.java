package com.bbt.main.view;

import com.bbt.main.ui.R;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.view.View.*;
/**
 * 
 * @author liyu
 *
 */
public class BannerLayout extends View{
	
	private final Context mContext;

    private final ViewGroup mPopupLayout;

    private final ViewGroup mParentView;
    private final int screenHeight;
    
    public BannerLayout( final Context context) {
        super(context);

        mContext = context;

        DisplayMetrics metric = new DisplayMetrics();
		metric = context.getApplicationContext().getResources().getDisplayMetrics();
		screenHeight = metric.heightPixels;
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        final WindowManager mWinManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPopupLayout = (LinearLayout) inflater.inflate(R.layout.dialog_alert_layout, null);
        mPopupLayout.setVisibility(GONE);

        params.width = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.MATCH_PARENT;
        // Default variant
        // params.windowAnimations = android.R.style.Animation_Translucent;

        mParentView = new FrameLayout(mContext);

        mWinManager.addView(mParentView, params);

        mParentView.addView(mPopupLayout);
        mPopupLayout.setVisibility(GONE);
    }
    /**
     * Shows view
     */
    public void show(){
    	
    	// ? 怎么自动来
    	
    	
       /*  Animation in = AnimationUtils.loadAnimation(this.mContext, android.R.anim.fade_in);*/
        
        
    	mPopupLayout.setY(screenHeight);
        mPopupLayout.setVisibility(VISIBLE);
        ObjectAnimator.ofFloat(mPopupLayout, "Y", mPopupLayout.getY()-180)
        .setDuration(500)
        .start();
        
    }

    /**
     * Hides view
     */
    public void hide() {
        mPopupLayout.setVisibility(GONE);
    }

}
