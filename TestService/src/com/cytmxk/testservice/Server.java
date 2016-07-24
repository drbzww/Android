package com.cytmxk.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Process;

public class Server extends Service {
	
	private Handler mHandler = null;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		MyApp mMyApp = (MyApp) getApplication();
		mHandler = mMyApp.getHandler();
		Message message = Message.obtain();
		Bundle data = new Bundle();
		data.putString("process", Process.myPid() + "");
		data.putString("thread", Thread.currentThread().getId() + "");
		message.setData(data);
		mHandler.sendMessage(message);
		
	}

}
