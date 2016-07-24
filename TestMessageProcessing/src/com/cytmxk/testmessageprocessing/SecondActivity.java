package com.cytmxk.testmessageprocessing;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends Activity{
	
	private Button mFirstBeautyButton = null;
	private Button mSecondBeautyButton = null;
	private Button mThreeBeautyButton = null;
	private Button mFourBeautyButton = null;
	private Button mFiveBeautyButton = null;
	
	private static int index = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_second);
		
		initViews();
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
				index = 1;
				new Thread(new SubThread1(SecondActivity.this,index)).start();
				finish();
			}
		});

		mSecondBeautyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				index =2;
				new Thread(new SubThread1(SecondActivity.this,index)).start();
				finish();
			}
		});
		
		mThreeBeautyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				index = 3;
				new Thread(new SubThread1(SecondActivity.this,index)).start();
				finish();
			}
		});
		
		mFourBeautyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				index = 4;
				new Thread(new SubThread1(SecondActivity.this,index)).start();
				finish();
			}
		});
		
		mFiveBeautyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				index = 5;
				new Thread(new SubThread1(SecondActivity.this,index)).start();
				finish();
			}
		});
	}
}
