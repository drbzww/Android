package com.cytmxk.testservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FiveActivity extends Activity {

	private TextView mTextActivityInfo = null;
	private TextView mTextServiceInfo = null;
	private Button mButtonStartService = null;
	private Button mButtonSotpService = null;

	private BroadcastReceiver mBroadcastReceiver = null;
	
	private static final String SEND_PRIMISSION = "com.android.permission.SEND_BROADCAST";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_five);

		initViews();
		initOthers();
	}

	private void initOthers() {
		mBroadcastReceiver = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				if (null != intent) {
					mTextActivityInfo.setText("activity process id = "
							+ Process.myPid()
							+ "\n activity thread id = "
							+ Thread.currentThread().getId());
					mTextServiceInfo.setText("service process id = "
									+ intent.getExtras().getString("process")
									+ "\n service thread id = "
									+ intent.getExtras().getString("thread"));
				}
			}
		};
		IntentFilter mFilter = new IntentFilter();
		mFilter.addAction("com.cytmxk.testservice.mBroadcastReceiver");
		registerReceiver(mBroadcastReceiver, mFilter, SEND_PRIMISSION, null);

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
				startService(new Intent().setAction("com.cytmxk.testotherservice.FirstServer"));
			}
		});

		mButtonSotpService.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopService(new Intent().setAction("com.cytmxk.testotherservice.FirstServer"));
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(mBroadcastReceiver);
	}
}
