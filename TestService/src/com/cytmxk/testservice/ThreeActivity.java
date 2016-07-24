package com.cytmxk.testservice;

import com.cytmxk.testservice.ThreeServer.InfoBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThreeActivity extends Activity {

	private TextView mTextActivityInfo = null;
	private TextView mTextServiceInfo = null;
	private Button mButtonStartService = null;
	private Button mButtonSotpService = null;

	private ServiceConnection conn = null;
	private InfoBinder mInfoBinder = null;

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
				mInfoBinder = null;
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				mInfoBinder = (InfoBinder) service;
				if (null != mInfoBinder) {
					mTextActivityInfo.setText("activity process id = "
							+ Process.myPid()
							+ "\n activity thread id = "
							+ Thread.currentThread().getId());
					mTextServiceInfo.setText("service process id = "
									+ mInfoBinder.getProcessID()
									+ "\n service thread id = "
									+ mInfoBinder.getThreadID());
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
				bindService(new Intent(ThreeActivity.this, ThreeServer.class), conn, Context.BIND_AUTO_CREATE);
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
