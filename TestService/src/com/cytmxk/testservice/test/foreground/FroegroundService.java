package com.cytmxk.testservice.test.foreground;

import com.cytmxk.testservice.R;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FroegroundService extends Service {

	private static final int ONGOING_NOTIFICATION_ID = 1;
	private static final String TAG = "FroegroundService";

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		android.util.Log.i(TAG, "FroegroundService onBind() 被调用");
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		startForegroundService();
		android.util.Log.i(TAG, "FroegroundService onCreate() 被调用");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		android.util.Log.i(TAG, "FroegroundService onStartCommand() 被调用");
		return super.onStartCommand(intent, flags, startId);
	}

	private void startForegroundService() {

		Intent notificationIntent = new Intent(getApplicationContext(),
				ExampleActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(
				getApplicationContext(), 0, notificationIntent, 0);

		Notification.Builder builder = new Notification.Builder(
				getApplicationContext());
		builder.setSmallIcon(R.drawable.ic_launcher)
				.setTicker(getText(R.string.ticker_text))
				.setWhen(System.currentTimeMillis())
				.setContentTitle(getText(R.string.notification_title))
				.setContentText(getText(R.string.notification_message))
				.setContentIntent(pendingIntent);
		Notification notification = builder.build();
		startForeground(ONGOING_NOTIFICATION_ID, notification);

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		android.util.Log.i(TAG, "FroegroundService onDestroy() 被调用");
		stopForeground(false);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		android.util.Log.i(TAG, "FroegroundService onUnbind() 被调用");
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
		android.util.Log.i(TAG, "FroegroundServiceonRebind() 被调用");
	}

}
