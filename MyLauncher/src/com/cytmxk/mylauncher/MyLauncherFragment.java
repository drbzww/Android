package com.cytmxk.mylauncher;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MyLauncherFragment extends ListFragment {

	private static final String TAG = "MyLauncherFragment";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);

		PackageManager pm = getActivity().getPackageManager();
		List<ResolveInfo> activities = pm.queryIntentActivities(intent, 0);
		Log.i(TAG, "I have found " + activities.size() + " activities.");

		Collections.sort(activities, new Comparator<ResolveInfo>() {

			@Override
			public int compare(ResolveInfo lhs, ResolveInfo rhs) {
				// TODO Auto-generated method stub
				PackageManager pm = getActivity().getPackageManager();
				return String.CASE_INSENSITIVE_ORDER.compare(lhs.loadLabel(pm)
						.toString(), rhs.loadIcon(pm).toString());
			}

		});

		ArrayAdapter<ResolveInfo> adapter = new ArrayAdapter<ResolveInfo>(
				getActivity(), 0, activities) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				if (null == convertView) {
					convertView = getActivity().getLayoutInflater().inflate(
							R.layout.item_activity, parent, false);
				}
				PackageManager pm = getActivity().getPackageManager();
				TextView tv = (TextView) convertView
						.findViewById(R.id.tv_title_activity);
				ImageView iv = (ImageView) convertView
						.findViewById(R.id.iv_icon_activity);
				tv.setText(getItem(position).loadLabel(pm));
				iv.setImageDrawable(getItem(position).loadIcon(pm));
				return convertView;
			}

		};

		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		ResolveInfo resolveInfo = (ResolveInfo) l.getAdapter()
				.getItem(position);
		ActivityInfo activityInfo = resolveInfo.activityInfo;
		if (null == activityInfo) {
			return;
		}
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setClassName(activityInfo.applicationInfo.packageName,
				activityInfo.name);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_my_launcher, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.menu_item_show_tasks:
			Intent intent = new Intent(getActivity(), ShowTasksActivity.class);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
