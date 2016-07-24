package com.cytmxk.bindservice;

import com.cytmxk.androiddemo.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CounterActivity extends Activity implements ICounterCallback{
	
	private Button startOrStopButton = null;
	private TextView counterText = null;
	
	private ICounterService iCounterService = null;
	
	private boolean isStart = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.counter);
		
		initViews();
		Intent bindIntent = new Intent(this, CounterService.class);
		bindService(bindIntent, serviceConnection, Context.BIND_AUTO_CREATE);
	}

	private void initViews() {
		startOrStopButton = (Button) findViewById(R.id.start_stop_button);
		counterText = (TextView) findViewById(R.id.counter_text);
		
		startOrStopButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(isStart){
					isStart = false;
					startOrStopButton.setText(R.string.start_button);
					iCounterService.startCounter(0, CounterActivity.this);
				}else{
					isStart = true;
					startOrStopButton.setText(R.string.stop_button);
					iCounterService.stopCounter();
				}
			}
		});
	}

	@Override
	public void counter(int val) {
		// TODO Auto-generated method stub
		counterText.setText(String.valueOf(val));
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		unbindService(serviceConnection);
	}



	ServiceConnection serviceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			iCounterService = null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			iCounterService = ((CounterService.CounterBinder)service).getService();
		}
	};
}
