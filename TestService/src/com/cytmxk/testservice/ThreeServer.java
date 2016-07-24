package com.cytmxk.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;

public class ThreeServer extends Service {
	
	private InfoBinder mInfoBinder = null;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		mInfoBinder = new InfoBinder();
		return mInfoBinder;
	}

	
	class InfoBinder extends Binder{
		
		private String mProcessID = "";
		private String mThreadID = "";
		
		public InfoBinder() {
			super();
			// TODO Auto-generated constructor stub
			this.mProcessID = Process.myPid() + "";
			this.mThreadID = Thread.currentThread().getId() + "";
		}

		public String getProcessID() {
			return mProcessID;
		}

		public void setProcessID(String processID) {
			mProcessID = processID;
		}

		public String getThreadID() {
			return mThreadID;
		}

		public void setThreadID(String threadID) {
			mThreadID = threadID;
		}
		
	}

}
