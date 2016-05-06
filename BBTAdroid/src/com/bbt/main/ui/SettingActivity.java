package com.bbt.main.ui;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bbt.main.tool.PictureUtil;
import com.bbt.main.view.BannerLayout;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;
public class SettingActivity  extends Activity{
	PopupWindow mPopupWindow;
	private int mScreenWidth;
	private int mScreenHeight;
	private String mCurrentPhotoPath;
	private BannerLayout layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_layout);
		initPopuptWindow();
		layout = new BannerLayout( this);
		WindowManager.LayoutParams lp=getWindow().getAttributes();
		lp.screenBrightness=0.5f;
		lp.buttonBrightness = 0.1f;
		lp.dimAmount=1.0f;
		getWindow().setAttributes(lp);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
	}
	public void onChangeClick(View view){
		// ��ʾpopupWindow �������ɵ��
		/*getPopupWindowInstance();
		mPopupWindow.showAsDropDown(view);*/
		// �Լ���һ��Window����ʾ����
		WindowManager.LayoutParams lp=getWindow().getAttributes();
		lp.dimAmount=0.5f;
		getWindow().setAttributes(lp);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

		layout.show();
		initBottomWindow();
		
		
	}
	
	
	
	private void initBottomWindow(){
		
		/*LayoutParams mLayoutParam = new WindowManager.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,
				0,0,PixelFormat.TRANSPARENT);
        mLayoutParam.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL|LayoutParams.FLAG_NOT_FOCUSABLE
        		|LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        mLayoutParam.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParam.x = 100;
        
        mScreenHeight = getWindowManager().getDefaultDisplay().getHeight();
        mLayoutParam.y = mScreenHeight+500;
		//View view = getLayoutInflater().inflate(R.layout.dialog_alert_layout, null);
        Button btn = new Button(this);
		getWindowManager().addView(btn, mLayoutParam);*/
		
		
		
	}
	
	
	
	/*
	 * ��ȡPopupWindowʵ��
	 */
	private void getPopupWindowInstance() {
		if (null != mPopupWindow) {
			mPopupWindow.dismiss();
			return;
		} else {
			initPopuptWindow();
		}
	}
	
	private void initPopuptWindow() {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View popupWindow = layoutInflater.inflate(R.layout.dialog_alert_layout, null);
		// ��ȡ��Ļ��PopupWindow��width��height
		mScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
		mScreenHeight = getWindowManager().getDefaultDisplay().getHeight();
		mPopupWindow = new PopupWindow(popupWindow, mScreenWidth,
				mScreenHeight / 3);
		Button btn_take_photo = (Button) popupWindow
				.findViewById(R.id.btn_take_photo);
		Button btn_pick_photo = (Button) popupWindow
				.findViewById(R.id.btn_pick_photo);
		Button btn_cancel = (Button) popupWindow.findViewById(R.id.btn_cancel);

		btn_take_photo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				takePhoto();
			}
		});

		btn_pick_photo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK,
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, 10);
			}
		});
		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mPopupWindow.dismiss();
			}
		});
	}
	private void takePhoto() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		try {
			// ָ�����������Ƭ��λ��
			File f = createImageFile();
			takePictureIntent
					.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
			startActivityForResult(takePictureIntent, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private File createImageFile() throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String timeStamp = format.format(new Date());
		String imageFileName = "sheqing_" + timeStamp + ".jpg";

		File image = new File(PictureUtil.getAlbumDir(), imageFileName);
		mCurrentPhotoPath = image.getAbsolutePath();
		return image;	
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
			double newHeight) {
		// ��ȡ���ͼƬ�Ŀ�͸�
		float width = bgimage.getWidth();
		float height = bgimage.getHeight();
		// ��������ͼƬ�õ�matrix����
		Matrix matrix = new Matrix();
		// ������������
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// ����ͼƬ����
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
				(int) height, matrix, true);
		return bitmap;
	}

	public static String getRealFilePath(final Context context, final Uri uri) {
		if (null == uri)
			return null;
		final String scheme = uri.getScheme();
		String data = null;
		if (scheme == null)
			data = uri.getPath();
		else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
			data = uri.getPath();
		} else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
			Cursor cursor = context.getContentResolver().query(uri,
					new String[] { MediaStore.Images.ImageColumns.DATA }, null,
					null, null);
			if (null != cursor) {
				if (cursor.moveToFirst()) {
					int index = cursor
							.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
					if (index > -1) {
						data = cursor.getString(index);
					}
				}
				cursor.close();
			}
		}
		return data;
	}



	

}
