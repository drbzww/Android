package com.cytmxk.testinterprocesscommunication.volley;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

public class MyApplication extends Application {

	private static RequestQueue mRequestQueue;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mRequestQueue = Volley.newRequestQueue(getApplicationContext());
	}
	
	public static RequestQueue getRequestQueue() {
		return mRequestQueue;
	}
}
