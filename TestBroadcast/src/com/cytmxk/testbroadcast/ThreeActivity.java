package com.cytmxk.testbroadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class ThreeActivity extends Activity {
	
	private ImageView mBeautyImage = null;
	
	private static final String BROADCAST_COUNTER_ACTION = "com.cytmxk.testbroadcast.SHOW_ACTION";
	private Handler mHander = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.activity_three);
		
		initViews();
		init();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(Menu.NONE, 1, 0, R.string.show_menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(1==item.getItemId()){
			new BeautyShow().execute(1);
        }
		return super.onOptionsItemSelected(item);
	}

	private void init() {
		// TODO Auto-generated method stub
		mHander = new Handler(getMainLooper()){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case 1:
					mBeautyImage.setImageResource(R.drawable.beauty1);
					break;
				case 2:
					mBeautyImage.setImageResource(R.drawable.beauty2);
					break;
				case 3:
					mBeautyImage.setImageResource(R.drawable.beauty3);
					break;
				case 4:
					mBeautyImage.setImageResource(R.drawable.beauty4);
					break;
				case 5:
					mBeautyImage.setImageResource(R.drawable.beauty5);
					break;

				default:
					break;
				}
			}
		};
		MyGlobalVariables.setHander(mHander);
		new BeautyShow().execute(1);
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
			Intent intent = new Intent(BROADCAST_COUNTER_ACTION);
			if(1 == values[0]){
				intent.putExtra("index", 1);
				sendBroadcast(intent);
			}else if(2 == values[0]){
				intent.putExtra("index", 2);
				sendBroadcast(intent);
			}else if(3 == values[0]){
				intent.putExtra("index", 3);
				sendBroadcast(intent);
			}else if(4 == values[0]){
				intent.putExtra("index", 4);
				sendBroadcast(intent);
			}else if(5 == values[0]){
				intent.putExtra("index", 5);
				sendBroadcast(intent);
			}
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Intent intent = new Intent(BROADCAST_COUNTER_ACTION);
			if(5 == result){
				intent.putExtra("index", 5);
				sendBroadcast(intent);
			}
		}
	}
	
}
