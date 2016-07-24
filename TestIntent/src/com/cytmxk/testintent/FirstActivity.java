package com.cytmxk.testintent;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_first);
		Button btStrat = (Button) findViewById(R.id.bt_start_second_activity);
		btStrat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(SecondActivity.ACTION_START);
				List<ResolveInfo> activities = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
				android.util.Log.i("chenyang","activities.size() = " + activities.size());
				if (activities.size() > 0){
					startActivity(intent);
				}
			}
		});
		
		Button btSendBroadcast = (Button) findViewById(R.id.bt_send_broadcast);
		btSendBroadcast.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(FirstBroadcastReceiver.ACTION_START);
				intent.putExtra("EXTRA_NAME", "zhangfei");
				List<ResolveInfo> receivers = getPackageManager().queryBroadcastReceivers(intent, 0);
				android.util.Log.i("chenyang","receivers.size() = " + receivers.size());
				if (receivers.size() > 0){
					sendBroadcast(intent, FirstBroadcastReceiver.PERMISSION);
				}
			}
		});
		
	}
}
