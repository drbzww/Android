package com.cytmxk.testbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

public class ShowBeautyReceiver extends BroadcastReceiver {
	
	private Handler mHandler = null;
	
	public ShowBeautyReceiver() {
		super();
		// TODO Auto-generated constructor stub
		mHandler = MyGlobalVariables.getHander();
	}



	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		int index = intent.getIntExtra("index", 1);
		switch (index) {
		case 1:
			mHandler.sendEmptyMessage(1);
			break;
		case 2:
			mHandler.sendEmptyMessage(2);
			break;
		case 3:
			mHandler.sendEmptyMessage(3);
			break;
		case 4:
			mHandler.sendEmptyMessage(4);
			break;
		case 5:
			mHandler.sendEmptyMessage(5);
			break;
			

		default:
			break;
		}
	}

}
