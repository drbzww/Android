package com.cytmxk.testotherbroadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class FourActivity extends Activity {
	
	private ImageView mBeautyImage = null;
	
	private static final String BROADCAST_COUNTER_ACTION = "com.cytmxk.testbroadcast.SHOW_ACTION";
	
	private ShowBeautyReceiver mShowBeautyReceiver1 = null;
	private ShowBeautyReceiver mShowBeautyReceiver2 = null;
	private ShowBeautyReceiver mShowBeautyReceiver3 = null;
	private ShowBeautyReceiver mShowBeautyReceiver4 = null;
	private ShowBeautyReceiver mShowBeautyReceiver5 = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.activity_four);
		
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
			Intent intent = new Intent(BROADCAST_COUNTER_ACTION);
			sendOrderedBroadcast(intent, null);
		}
		return super.onOptionsItemSelected(item);
	}

	private void init() {
		// TODO Auto-generated method stub

		IntentFilter mIntentFilter1 = new IntentFilter(BROADCAST_COUNTER_ACTION);
		mIntentFilter1.setPriority(1);
		IntentFilter mIntentFilter2 = new IntentFilter(BROADCAST_COUNTER_ACTION);
		mIntentFilter2.setPriority(2);
		IntentFilter mIntentFilter3 = new IntentFilter(BROADCAST_COUNTER_ACTION);
		mIntentFilter3.setPriority(3);
		IntentFilter mIntentFilter4 = new IntentFilter(BROADCAST_COUNTER_ACTION);
		mIntentFilter4.setPriority(4);
		IntentFilter mIntentFilter5 = new IntentFilter(BROADCAST_COUNTER_ACTION);
		mIntentFilter5.setPriority(5);
		mShowBeautyReceiver1 = new ShowBeautyReceiver();
		mShowBeautyReceiver2 = new ShowBeautyReceiver();
		mShowBeautyReceiver3 = new ShowBeautyReceiver();
		mShowBeautyReceiver4 = new ShowBeautyReceiver();
		mShowBeautyReceiver5 = new ShowBeautyReceiver();
		registerReceiver(mShowBeautyReceiver1, mIntentFilter1);
		registerReceiver(mShowBeautyReceiver2, mIntentFilter2);
		registerReceiver(mShowBeautyReceiver3, mIntentFilter3);
		registerReceiver(mShowBeautyReceiver4, mIntentFilter4);
		registerReceiver(mShowBeautyReceiver5, mIntentFilter5);
	}

	private void initViews() {
		mBeautyImage = (ImageView) findViewById(R.id.beauty_image);
		mBeautyImage.setImageResource(R.drawable.beauty1);
	}
	
	public class ShowBeautyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub

			if(this.equals(mShowBeautyReceiver1)){
				mBeautyImage.setImageResource(R.drawable.beauty1);
			}else if(this.equals(mShowBeautyReceiver2)){
				mBeautyImage.setImageResource(R.drawable.beauty2);
			}else if(this.equals(mShowBeautyReceiver3)){
				mBeautyImage.setImageResource(R.drawable.beauty3);
			}else if(this.equals(mShowBeautyReceiver4)){
				mBeautyImage.setImageResource(R.drawable.beauty4);
			}else if(this.equals(mShowBeautyReceiver5)){
				mBeautyImage.setImageResource(R.drawable.beauty5);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
}
