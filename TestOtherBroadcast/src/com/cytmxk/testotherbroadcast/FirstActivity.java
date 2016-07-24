package com.cytmxk.testotherbroadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

public class FirstActivity extends Activity {
	
	private ImageView mBeautyImage = null;
	
	private static final String BROADCAST_SHOW_ACTION = "com.cytmxk.testbroadcast.SHOW_ACTION";
	
	private BroadcastReceiver mShowBeautyReceiver = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.activity_first);
		
		initViews();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		mShowBeautyReceiver = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				String mShow = intent.getStringExtra("show");
				if("show".equals(mShow)){
					new BeautyShow().execute(1);
				}
				
			}
		};
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		IntentFilter mIntentFilter = new IntentFilter(BROADCAST_SHOW_ACTION);
		registerReceiver(mShowBeautyReceiver, mIntentFilter);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		unregisterReceiver(mShowBeautyReceiver);
	}

	private void initViews() {
		mBeautyImage = (ImageView) findViewById(R.id.beauty_image);
		
	}
	
	class BeautyShow extends AsyncTask<Integer,Integer,Integer>{
		
		int index = 0;

		@Override
		protected Integer doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			for(index = params[0] ; index<= 5 ; index ++){
				publishProgress(index);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return index - 1;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			if(1 == values[0]){
				mBeautyImage.setImageResource(R.drawable.beauty1);
			}else if(2 == values[0]){
				mBeautyImage.setImageResource(R.drawable.beauty2);
			}else if(3 == values[0]){
				mBeautyImage.setImageResource(R.drawable.beauty3);
			}else if(4 == values[0]){
				mBeautyImage.setImageResource(R.drawable.beauty4);
			}else if(5 == values[0]){
				mBeautyImage.setImageResource(R.drawable.beauty5);
			}
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(5 == result){
				mBeautyImage.setImageResource(R.drawable.beauty5);
			}
		}
	}
}
