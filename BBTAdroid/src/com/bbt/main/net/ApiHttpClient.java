package com.bbt.main.net;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Context;
import android.util.Log;

public class ApiHttpClient {
	
    
    private static String API_URL = "http://192.168.191.1:8080/BBTWebServer/%s";
    // public final static String HOST = "192.168.1.46";
    // private static String API_URL = "http://192.168.1.46/%s";
    public static final String DELETE = "DELETE";
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
	
    public static AsyncHttpClient client;

    public ApiHttpClient() {}

    public static AsyncHttpClient getHttpClient() {
        return client;
    }

    public static void cancelAll(Context context) {
        client.cancelRequests(context, true);
    }

    
    public static void log(String log) {
        Log.d("BaseApi", log);
    }
    
    public static String getAbsoluteApiUrl(String partUrl) {
        String url = String.format(API_URL, partUrl);
        Log.d("BASE_CLIENT", "request:" + url);
        return url;
    }
    
    public static void get(String partUrl, AsyncHttpResponseHandler handler) {
        client.get(getAbsoluteApiUrl(partUrl), handler);
        log(new StringBuilder("GET ").append(partUrl).toString());
    }

    public static void get(String partUrl, RequestParams params,
            AsyncHttpResponseHandler handler) {
        client.get(getAbsoluteApiUrl(partUrl), params, handler);
        log(new StringBuilder("GET ").append(partUrl).append("&")
                .append(params).toString());
    }


    public static void post(String partUrl, RequestParams params,
            AsyncHttpResponseHandler handler) {
        client.post(getAbsoluteApiUrl(partUrl), params, handler);
        
        log(new StringBuilder("POST ").append(partUrl).append("&")
                .append(params).toString());
    }


}
