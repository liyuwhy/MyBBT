package com.bbt.main.dao;

import java.io.FileNotFoundException;
import java.util.List;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.GetFileCallback;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.ProgressCallback;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;
import com.bbt.main.bean.User;

import android.os.Environment;

public class UserDao {

	public static void queryByName(String username) {
		AVQuery<AVUser> query = new AVQuery<AVUser>("_User");
		query.whereEqualTo("username", username);
		query.findInBackground(new FindCallback<AVUser>() {
			@Override
			public void done(List<AVUser> list, AVException e) {
				if (list.get(0) != null) {
					User user = User.parseToMyUser(list.get(0));
				}
			}
		});
	}

	// ע��
	public static void signUp(String username, String password, String email, SignUpCallback signUpCallback) {
		AVUser user = new AVUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.signUpInBackground(signUpCallback);
	}

	// ע��
	public static void signUp(User myUser, SignUpCallback callback) {
		AVUser avUser = User.parseToAVUser(myUser);
		avUser.signUpInBackground(callback);
	}

	// ��¼ ��phoneNumber ��usernameʹ��
	public static void login(String phoneNumber, String password, LogInCallback<AVUser> callback) {
		AVUser.loginByMobilePhoneNumberInBackground(phoneNumber, password, callback);
	}

	// ע��
	public static void logout() {
		AVUser.logOut();
	}
	
	/**
	 * 
	 */
	public static void getDefaultPic(GetDataCallback callBack ){
		
		
		GetFileCallback<AVFile> callback = new GetFileCallback<AVFile>() {

			@Override
			public void done(AVFile arg0, AVException arg1) {
				// TODO Auto-generated method stub
				
			}
		};
	
		
		AVFile file = null;
		try {
			
	
			file = AVFile.withObjectId("b2af66e8bf93ac6f.jpg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AVException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// �ļ��ϴ�
	public static void fileUploadToAV() {
		try {
			final AVFile file = AVFile.withAbsoluteLocalPath("LeanCloud.png",
					Environment.getExternalStorageDirectory() + "/LeanCloud.png");
			file.saveInBackground(new SaveCallback() {
				@Override
				public void done(AVException e) {
					// �ɹ���ʧ�ܴ���...
					file.getUrl();
				}
			}, new ProgressCallback() {
				@Override
				public void done(Integer integer) {
					// �ϴ��������ݣ�integer ���� 0 �� 100��
				}
			});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	
	
}
