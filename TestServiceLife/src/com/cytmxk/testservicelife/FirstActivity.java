package com.cytmxk.testservicelife;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends Activity {
	
	private Button mBtnStartService = null;
	private Button mBtnStopService = null;
	private Button mBtnBindService = null;
	private Button mBtnUnbindService = null;
	
	private ServiceConnection mServiceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			
		}
	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_first);
		
		initViews();
	}

	private void initViews() {
		mBtnStartService = (Button) findViewById(R.id.button_start_service);
		mBtnStopService = (Button) findViewById(R.id.button_stop_service);
		mBtnBindService = (Button) findViewById(R.id.button_bind_service);
		mBtnUnbindService = (Button) findViewById(R.id.button_unbind_service);
		
		mBtnStartService.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startService(new Intent(FirstActivity.this,TestService.class));
			}
		});
		
		mBtnStopService.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopService(new Intent(FirstActivity.this,TestService.class));
			}
		});
		
		mBtnBindService.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bindService(new Intent(FirstActivity.this,TestService.class), mServiceConnection, Context.BIND_AUTO_CREATE);
			}
		});
		
		mBtnUnbindService.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				unbindService(mServiceConnection);
			}
		});
	}
}
