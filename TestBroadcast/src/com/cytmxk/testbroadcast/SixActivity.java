package com.cytmxk.testbroadcast;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SixActivity extends Activity {

	private Button mShowBeautyButton = null;

	private static final String BROADCAST_SHOW_ACTION = "com.cytmxk.testbroadcast.SHOW_ACTION";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_six);
		initviews();
	}

	private void initviews() {
		mShowBeautyButton = (Button) findViewById(R.id.show_beauty_button);
		mShowBeautyButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent1 = new Intent();
				intent1.setComponent(new ComponentName(
						"com.cytmxk.testotherbroadcast",
						"com.cytmxk.testotherbroadcast.SecondActivity"));
				startActivity(intent1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent intent2 = new Intent(BROADCAST_SHOW_ACTION);
				intent2.putExtra("show", "show");
				sendBroadcast(intent2);
			}
		});
	}
}
