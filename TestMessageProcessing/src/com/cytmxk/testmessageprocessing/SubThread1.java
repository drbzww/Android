package com.cytmxk.testmessageprocessing;

import android.app.Activity;
import android.os.Handler;

public class SubThread1 implements Runnable {
	
	private MyApp mMyApp = null;
	private int mIndex = 0;
	private Handler mHandler = null;

	public SubThread1(Activity activity,int index) {
		// TODO Auto-generated constructor stub
		mMyApp = (MyApp) activity.getApplication();
		mHandler = mMyApp.getHandler();
		this.mIndex = index;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

		mHandler.sendEmptyMessage(mIndex);
	}

}
