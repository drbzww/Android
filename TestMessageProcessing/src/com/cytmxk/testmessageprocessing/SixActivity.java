package com.cytmxk.testmessageprocessing;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class SixActivity extends Activity {
	
	private Button mFirstBeautyButton = null;
	private Button mSecondBeautyButton = null;
	private Button mThreeBeautyButton = null;
	private Button mFourBeautyButton = null;
	private Button mFiveBeautyButton = null;
	
	private MyApp mMyApp = null;
	private Handler mHandler = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_six);
		
		init();
		initViews();
	}

	private void init() {
		mMyApp = (MyApp) getApplication();
		mHandler = mMyApp.getHandler();
	}
	
	private void initViews() {
		mFirstBeautyButton = (Button) findViewById(R.id.first_girl);
		mSecondBeautyButton = (Button) findViewById(R.id.second_girl);
		mThreeBeautyButton = (Button) findViewById(R.id.three_girl);
		mFourBeautyButton = (Button) findViewById(R.id.four_girl);
		mFiveBeautyButton = (Button) findViewById(R.id.five_girl);
		
		mFirstBeautyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mHandler.sendEmptyMessage(1);
				finish();
			}
		});

		mSecondBeautyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mHandler.sendEmptyMessage(2);
				finish();
			}
		});
		
		mThreeBeautyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mHandler.sendEmptyMessage(3);
				finish();
			}
		});
		
		mFourBeautyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mHandler.sendEmptyMessage(4);
				finish();
			}
		});
		
		mFiveBeautyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mHandler.sendEmptyMessage(5);
				finish();
			}
		});
	}
}
