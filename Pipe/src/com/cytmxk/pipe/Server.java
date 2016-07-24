package com.cytmxk.pipe;

import java.lang.reflect.Method;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Server extends Service {

	private static final String TAG = "com.cytmxk.ashmem.Server";
	
	private MemoryService mMemoryService = null;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		android.util.Log.i(TAG, "start onCreate()");
		
		mMemoryService = new MemoryService();
		try {
			Class<?> clz = Class.forName("android.os.ServiceManager");
			Method mMethod = clz.getMethod("addService", String.class, IBinder.class);
			mMethod.invoke(null, "AnonymousSharedMemory",mMemoryService);
			android.util.Log.i(TAG, "Succeed to add memory service.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			android.util.Log.i(TAG, "Failed to add memory service.");
			android.util.Log.i(TAG,"e = " + e);
		}
	}
	
}
