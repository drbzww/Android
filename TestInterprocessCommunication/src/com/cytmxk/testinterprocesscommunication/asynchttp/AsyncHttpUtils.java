package com.cytmxk.testinterprocesscommunication.asynchttp;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

public class AsyncHttpUtils {

	private static AsyncHttpClient mAsyncHttpClient = new AsyncHttpClient();
	
	public static void get(String url,MyAsyncHttpResponseHandler responseHandler) {
		mAsyncHttpClient.get(url, responseHandler);
	}
	
	public static void post(String url,RequestParams params, MyAsyncHttpResponseHandler responseHandler) {
		mAsyncHttpClient.post(url, params, responseHandler);
	}
}
