package com.cytmxk.testservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends Activity {

	private TextView mTextActivityInfo = null;
	private TextView mTextServiceInfo = null;
	private Button mButtonStartService = null;
	private Button mButtonSotpService = null;

	private Handler mHandler = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_first);

		initViews();
		initOthers();
	}

	private void initOthers() {
		mHandler = new Handler(getMainLooper()) {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);

				if (null != msg) {
					mTextActivityInfo.setText("activity process id = "
							+ Process.myPid()
							+ "\n activity thread id = "
							+ Thread.currentThread().getId());
					mTextServiceInfo.setText("service process id = "
									+ msg.getData().getString("process")
									+ "\n service thread id = "
									+ msg.getData().getString("thread"));
				}
			}
		};
		MyApp mMyApp = (MyApp) getApplication();
		mMyApp.setHandler(mHandler);
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
				startService(new Intent(FirstActivity.this, Server.class));
			}
		});

		mButtonSotpService.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopService(new Intent(FirstActivity.this, Server.class));
			}
		});
	}
}
