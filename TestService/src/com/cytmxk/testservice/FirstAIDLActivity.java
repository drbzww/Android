package com.cytmxk.testservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstAIDLActivity extends Activity {

	private TextView mTextActivityInfo = null;
	private TextView mTextServiceInfo = null;
	private Button mButtonStartService = null;
	private Button mButtonSotpService = null;

	private ServiceConnection conn = null;
	private IInfoService mIInfoService = null;
	
	private static final String TAG = "com.cytmxk.testservice.FirstAIDLActivity";

	private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
		
		@Override
		public void binderDied() {
			// TODO Auto-generated method stub
			android.util.Log.i(TAG,"远程Binder 死亡");
			if (null == mIInfoService) {
				return;
			}
			mIInfoService.asBinder().unlinkToDeath(mDeathRecipient, 0);
			mIInfoService = null;
			//TODO：这里可以重新绑定远程Service
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_three);

		initViews();
		initOthers();
	}

	private void initOthers() {
		
		conn = new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				
				mIInfoService = IInfoService.Stub.asInterface(service);
				try {
					service.linkToDeath(mDeathRecipient, 0);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					android.util.Log.i(TAG,"设置死亡代理失败");
				}
				if (null != mIInfoService) {
					mTextActivityInfo.setText("activity process id = "
							+ Process.myPid()
							+ "\n activity thread id = "
							+ Thread.currentThread().getId());
					try {
						mTextServiceInfo.setText("service process id = "
										+ mIInfoService.getProcessID()
										+ "\n service thread id = "
										+ mIInfoService.getThreadID());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						android.util.Log.i(TAG,"e = " + e);
					}
				}
			}
		};
	}

	private void initViews() {
		mTextActivityInfo = (TextView) findViewById(R.id.text_activity_info);
		mTextServiceInfo = (TextView) findViewById(R.id.text_service_info);
		mButtonStartService = (Button) findViewById(R.id.button_start_service);
		mButtonSotpService = (Button) findViewById(R.id.button_stop_service);

		mButtonStartService.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bindService(new Intent().setAction("com.cytmxk.testotherservice.FirstAIDLServer"), conn, Context.BIND_AUTO_CREATE);
			}
		});

		mButtonSotpService.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				unbindService(conn);
			}
		});
	}
}
