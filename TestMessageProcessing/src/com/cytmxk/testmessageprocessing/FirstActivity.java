package com.cytmxk.testmessageprocessing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FirstActivity extends Activity {
	
	private Button mSelectBeautyButton = null;
	private ImageView mBeautyImageView = null;
	
	private Handler mHandler = null;
	private MyApp mMyApp = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_first);
		
		init();
		initViews();
	}

	private void init() {
		mHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				
				if(1 == msg.what){
					android.util.Log.i("chenyang","msg.what = " + msg.what);
					mBeautyImageView.setImageResource(R.drawable.beauty1);
				} else if (2 == msg.what) {
					android.util.Log.i("chenyang","msg.what = " + msg.what);
					mBeautyImageView.setImageResource(R.drawable.beauty2);
				} else if (3 == msg.what) {
					android.util.Log.i("chenyang","msg.what = " + msg.what);
					mBeautyImageView.setImageResource(R.drawable.beauty3);
				} else if (4 == msg.what) {
					android.util.Log.i("chenyang","msg.what = " + msg.what);
					mBeautyImageView.setImageResource(R.drawable.beauty4);
				} else if (5 == msg.what) {
					android.util.Log.i("chenyang","msg.what = " + msg.what);
					mBeautyImageView.setImageResource(R.drawable.beauty5);
				} else {
					android.util.Log.i("chenyang","msg.what = " + msg.what);
				}
			}
		};
		
		mMyApp = (MyApp) getApplication();
		mMyApp.setHandler(mHandler);
	}

	private void initViews() {
		mSelectBeautyButton = (Button) findViewById(R.id.seclect_beauty);
		mBeautyImageView = (ImageView) findViewById(R.id.beauty_image_view);
		mSelectBeautyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*通过Application实现共享handler，然后在SecondActivity中
				 * 创建子线程，并向主线程发送消息，从而控制FirstActivity中mBeautyImageView
				 * 切换图片*/
				Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
				startActivity(intent);
				
			}
		});
	}
}
