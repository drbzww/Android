package com.cytmxk.testservicelife;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TestService extends Service {
	
	private static final String TAG = "android.app.Service.TestService"; 

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		android.util.Log.i(TAG,"onBind");
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		android.util.Log.i(TAG,"onCreate");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		android.util.Log.i(TAG,"onStart");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		android.util.Log.i(TAG,"onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		android.util.Log.i(TAG,"onDestroy");
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		android.util.Log.i(TAG,"onUnbind");
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
		android.util.Log.i(TAG,"onRebind");
	}
	
}
