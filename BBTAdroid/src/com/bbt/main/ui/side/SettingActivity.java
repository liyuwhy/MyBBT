package com.bbt.main.ui.side;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.Header;

import com.bbt.main.net.ApiHttpClient;
import com.bbt.main.net.BBTApi;
import com.bbt.main.tool.PictureUtil;
import com.bbt.main.tool.SPUtil;
import com.bbt.main.ui.R;
import com.bbt.main.view.BannerLayout;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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

public class SettingActivity extends Activity {

	private static final String TAG = "SettingActivity";

	private static final int REQUEST_CODE_takePhoto = 0x11;
	private static final int REQUEST_CODE_GET_PHOTO = 0x22;
	private static final int REQUEST_CODE_CROP = 0x33;

	PopupWindow mPopupWindow;

	private int mScreenWidth;

	private int mScreenHeight;

	private String mCurrentPhotoPath;

	private BannerLayout layout;

	private Dialog dialog;

	private final static int CROP = 200;

	private Uri takePhotoUri;

	private File imgFile;

	private ImageView imgUserIcon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set);
		
		imgUserIcon =(ImageView)findViewById(R.id.setting_id_head);
		BBTApi.displayHeadImg(imgUserIcon, new SPUtil(this).getUserIcon());
	}

	private void showDialog() {
		View view = getLayoutInflater().inflate(R.layout.photo_choose_dialog, null);
		dialog = new Dialog(this, R.style.transparentFrameWindowStyle);
		dialog.setContentView(view, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		android.view.Window window = dialog.getWindow();
		// 设置显示动画
		window.setWindowAnimations(R.style.main_menu_animstyle);
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		// Y设置大于屏幕 它也会自动显示在窗口下面
		wl.y = 1000;
		// 以下这两句是为了保证按钮可以水平满屏
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		// 设置显示位置
		dialog.onWindowAttributesChanged(wl);
		// 设置点击外围解散
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
	}

	public void onChangeClick(View view) {
		Log.d(TAG, "onClickTheChange");
		switch (view.getId()) {
		case R.id.dialog_id_cancel:
			dialog.cancel();
			break;
		case R.id.dialog_id_take_photo:
			dialog.dismiss();
			takePhoto();
			break;
		case R.id.dialog_id_pic:
			dialog.dismiss();
			Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(intent, REQUEST_CODE_GET_PHOTO);
			break;
		case R.id.setting_id_head:
			if (dialog == null) {
				showDialog();
			} else {
				dialog.show();
			}
			break;
		default:
			break;
		}
	}

	private void takePhoto() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		try {
			imgFile = createImageFile();
			takePhotoUri = Uri.fromFile(imgFile);
			takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, takePhotoUri);
			startActivityForResult(takePictureIntent, REQUEST_CODE_takePhoto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private File createImageFile() throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String timeStamp = format.format(new Date());
		String imageFileName = "BBT_Head"+timeStamp+".jpg";

		File image = new File(PictureUtil.getAlbumDir(), imageFileName);
		mCurrentPhotoPath = image.getAbsolutePath();
		return image;
	}

	/**
	 * 拍照后裁剪
	 * 
	 * @param data
	 *            原始图片
	 * @param output
	 *            裁剪后图片
	 */
	private void startActionCrop(Uri data) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(data, "image/*");
		Uri uri = null;
		try {
			imgFile = createImageFile();
			uri = Uri.fromFile(imgFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		intent.putExtra("output", uri);
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);// 裁剪框比例
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", CROP);// 输出图片大小
		intent.putExtra("outputY", CROP);
		intent.putExtra("scale", true);// 去黑边
		intent.putExtra("scaleUpIfNeeded", true);// 去黑边
		startActivityForResult(intent, REQUEST_CODE_CROP);
	}

	@Override
	public void onActivityResult(final int requestCode, final int resultCode, final Intent imageReturnIntent) {

		switch (requestCode) {

		case REQUEST_CODE_takePhoto:
			startActionCrop(takePhotoUri);// 拍照后裁剪
			
			break;
		
		
		case REQUEST_CODE_GET_PHOTO:
			 startActionCrop(imageReturnIntent.getData());
			 break;

		case REQUEST_CODE_CROP:
			Toast.makeText(this, "get finish", 1000 * 5).show();
			uploadNewPhoto();

			break;

		}
	}

	/**
	 * 上传新照片
	 */
	private void uploadNewPhoto() {

		try {
			BBTApi.updatePortrait(new SPUtil(this).getPhoneNumber(), imgFile, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
					Toast.makeText(SettingActivity.this, "上传成功" + new String(arg2), 1000 * 5).show();
				}

				@Override
				public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
					// TODO Auto-generated method stub

				}
			});
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
		// 获取这个图片的宽和高
		float width = bgimage.getWidth();
		float height = bgimage.getHeight();
		// 创建操作图片用的matrix对象
		Matrix matrix = new Matrix();
		// 计算宽高缩放率
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 缩放图片动作
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width, (int) height, matrix, true);
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
					new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null);
			if (null != cursor) {
				if (cursor.moveToFirst()) {
					int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
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
