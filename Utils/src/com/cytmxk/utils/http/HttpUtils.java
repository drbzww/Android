package com.cytmxk.utils.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class HttpUtils implements Runnable{

	private final static String TAG = "com.cytmxk.utils.http.HttpUtils";
	private static AndroidHttpClient client = null;
	private static BasicHttpContext context = null;
	private String _URI = "http://www.baidu.com";
	private HttpGet requestGet = null;
	private HttpPost requestPost = null;
	private Handler handler = null;
	private List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
	private String is = "";
	private boolean isRun = false;

	static {
		client = AndroidHttpClient.newInstance("");
		context = new BasicHttpContext();
		context.setAttribute(ClientContext.COOKIE_STORE, new BasicCookieStore());
		
	}
	
	public HttpUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HttpUtils(String _URI, Handler handler, List<BasicNameValuePair> params, String is) {
		super();
		this._URI = _URI;
		this.handler = handler;
		this.params = params;
		this.is = is;
	}
	
	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	public void executeGet() {
		try {
			String param = URLEncodedUtils.format(params, "UTF-8");
			requestGet = new HttpGet(_URI + "?" + param);
			HttpResponse httpResponse = client.execute(requestGet, context);
//			android.util.Log.i(TAG, "resCode = " + httpResponse.getStatusLine().getStatusCode()); //获取响应码  
//			android.util.Log.i(TAG, "result = " + EntityUtils.toString(httpResponse.getEntity(), "utf-8"));//获取服务器响应内容  
			Message msg = new Message();
			msg.what = 0;
	        Bundle data = new Bundle();
	        data.putInt("resCode",httpResponse.getStatusLine().getStatusCode());
	        data.putString("result",EntityUtils.toString(httpResponse.getEntity(), "utf-8"));
	        msg.setData(data);
	        handler.sendMessage(msg);
	        isRun = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void executePost() {
		try {
			requestPost = new HttpPost(_URI); 
			try {
				requestPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpResponse httpResponse = client.execute(requestPost, context);
//			android.util.Log.i(TAG, "resCode = " + httpResponse.getStatusLine().getStatusCode()); //获取响应码  
//			android.util.Log.i(TAG, "result = " + EntityUtils.toString(httpResponse.getEntity(), "utf-8"));//获取服务器响应内容  
			Message msg = new Message();
			msg.what = 1;
	        Bundle data = new Bundle();
	        data.putInt("resCode",httpResponse.getStatusLine().getStatusCode());
	        data.putString("result",EntityUtils.toString(httpResponse.getEntity(), "utf-8"));
	        msg.setData(data);
	        handler.sendMessage(msg);
	        isRun = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(isRun == true){
			return;
		}
		isRun = true;
		if("get".equals(is)){
			executeGet();
		}else if("post".equals(is)){
			executePost();
		}
	}
	

}
