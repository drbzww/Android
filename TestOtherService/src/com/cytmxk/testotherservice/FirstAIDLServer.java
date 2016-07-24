package com.cytmxk.testotherservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;

public class FirstAIDLServer extends Service {

	private IInfoService.Stub mIInfoService = new IInfoService.Stub(){

		@Override
		public String getProcessID() throws RemoteException {
			// TODO Auto-generated method stub
			return Process.myPid() + "";
		}

		@Override
		public String getThreadID() throws RemoteException {
			// TODO Auto-generated method stub
			return Thread.currentThread().getId() + "";
		}};
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mIInfoService;
	}

}
