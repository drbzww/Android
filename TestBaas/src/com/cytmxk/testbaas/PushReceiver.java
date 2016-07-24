package com.cytmxk.testbaas;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import cn.bmob.push.PushConstants;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

public class PushReceiver extends BroadcastReceiver {

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String message = "";
		if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
			String msg = intent
					.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING);
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
			JSONTokener jsonTokener = new JSONTokener(msg);
			try {
				JSONObject object = (JSONObject) jsonTokener.nextValue();
				message = object.getString("alert");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			// 获取系统Notification服务
			NotificationManager manager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
			// 设置Notification相关属性
			Notification.Builder builder = new Notification.Builder(context);
			builder.setSmallIcon(R.drawable.ic_launcher)
					.setWhen(System.currentTimeMillis()).setAutoCancel(false)
					.setContentTitle("TestBmob").setContentText(message);
			manager.notify(R.drawable.ic_launcher, builder.build());
		}
	}

}
