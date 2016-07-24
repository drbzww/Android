package com.cytmxk.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;

public class SecondServer extends Service {
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		Bundle data = new Bundle();
		data.putString("process", Process.myPid() + "");
		data.putString("thread", Thread.currentThread().getId() + "");
		Intent intent = new Intent("com.cytmxk.testservice.mBroadcastReceiver");
		intent.putExtras(data);
		sendBroadcast(intent);
	}

}
