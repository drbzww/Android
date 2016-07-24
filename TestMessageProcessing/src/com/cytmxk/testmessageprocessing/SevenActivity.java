package com.cytmxk.testmessageprocessing;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SevenActivity extends Activity {
	
	private ImageView mBeautyImageView = null;
	private Button mBtStopShow = null;
	
	private AsyncTask<Integer,Integer,Integer> mAsyncTask = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_seven);
		
		initViews();
		init();
	}

	private void init() {
		mAsyncTask = new BeautyShow();
		mAsyncTask.execute(1);
	}

	private void initViews() {
		mBeautyImageView = (ImageView) findViewById(R.id.beauty_image_view);
		mBtStopShow = (Button) findViewById(R.id.bt_stop_show);
		mBtStopShow.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!mAsyncTask.isCancelled()) {
					mAsyncTask.cancel(true);//该方法调用后onPostExecute不会被调用，而是调用onCancelled
				}
			}
		});
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
				//Escape early if cancel() is called
				if (isCancelled()) {
					break;
				}
			}
			return index - 1;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			if(1 == values[0]){
				mBeautyImageView.setImageResource(R.drawable.beauty1);
			}else if(2 == values[0]){
				mBeautyImageView.setImageResource(R.drawable.beauty2);
			}else if(3 == values[0]){
				mBeautyImageView.setImageResource(R.drawable.beauty3);
			}else if(4 == values[0]){
				mBeautyImageView.setImageResource(R.drawable.beauty4);
			}else if(5 == values[0]){
				mBeautyImageView.setImageResource(R.drawable.beauty5);
			}
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(5 == result){
				mBeautyImageView.setImageResource(R.drawable.beauty5);
			}
		}
		
		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
			mBeautyImageView.setImageResource(R.drawable.beauty1);
		}
	}
}
