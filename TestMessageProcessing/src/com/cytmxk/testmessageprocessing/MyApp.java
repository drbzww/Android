package com.cytmxk.testmessageprocessing;

import android.app.Application;
import android.os.Handler;

public class MyApp extends Application {
	
	private Handler mHandler = null;

	public Handler getHandler() {
		return mHandler;
	}

	public void setHandler(Handler handler) {
		mHandler = handler;
	}
}
