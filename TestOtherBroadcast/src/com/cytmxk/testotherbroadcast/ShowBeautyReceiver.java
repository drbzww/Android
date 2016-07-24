package com.cytmxk.testotherbroadcast;

import com.cytmxk.testotherbroadcast.PermissionActivity3.BeautyShow;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ShowBeautyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		String mShow = intent.getStringExtra("show");
		if("show".equals(mShow)){
			new BeautyShow().execute(1);
		}
	}

}
