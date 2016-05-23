package com.bbt.main.net;

import java.io.File;
import java.io.FileNotFoundException;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class BBTApi {
	
	
    public static void updatePortrait(String phone, File portrait,
            AsyncHttpResponseHandler handler) throws FileNotFoundException {
        RequestParams params = new RequestParams();
        params.put("phone", phone);
        params.put("icon", portrait);
        ApiHttpClient.post("iconModify", params, handler);
    }

}
