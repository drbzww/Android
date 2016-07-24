package com.cytmxk.testintent;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FirstBroadcastReceiver extends BroadcastReceiver {

	public static final String ACTION_START = "com.cytmxk.testintent.action.STRAT_FIRSTBROADCASTRECEIVER";
	public static final String PERMISSION = "com.cytmxk.testintent.permission.RECV_SHOW";
	
	@TargetApi(12)
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		String name = intent.getExtras().getString("EXTRA_NAME", "name");
		android.util.Log.i("chenyang","onReceive name = " + name);
	}

}
