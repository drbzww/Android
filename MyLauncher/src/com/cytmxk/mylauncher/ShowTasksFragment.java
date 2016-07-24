package com.cytmxk.mylauncher;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ShowTasksFragment extends ListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActivityManager activityManager = (ActivityManager) getActivity()
				.getSystemService(FragmentActivity.ACTIVITY_SERVICE);
		final PackageManager pm = getActivity().getPackageManager();
		List<RunningAppProcessInfo> runningAppProcesses = activityManager
				.getRunningAppProcesses();

		android.util.Log.i("chenyang", "runningTasks.size() = "
				+ runningAppProcesses.size());
		ArrayAdapter<RunningAppProcessInfo> adapter = new ArrayAdapter<ActivityManager.RunningAppProcessInfo>(
				getActivity(), 0, runningAppProcesses) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				if (null == convertView) {
					convertView = getActivity().getLayoutInflater().inflate(
							R.layout.item_task, parent, false);
				}
				RunningAppProcessInfo appProcessInfo = getItem(position);
				ImageView iv = (ImageView) convertView
						.findViewById(R.id.iv_icon_task);
				TextView tv = (TextView) convertView
						.findViewById(R.id.tv_title_task);
				iv.setImageDrawable(ApplicationUtils.getAppIcon(pm,
						appProcessInfo.pkgList[0]));
				tv.setText(ApplicationUtils.getAppName(pm,
						appProcessInfo.pkgList[0]));
				return convertView;
			}

		};

		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}
}
