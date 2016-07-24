package com.cytmxk.infomationobtain.application;

import java.util.ArrayList;
import java.util.List;
import com.cytmxk.infomationobtain.R;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class ApplicationFragment extends Fragment implements
		View.OnClickListener {

	public static final int ALL_APP = 0;
	public static final int SYSTEM_APP = 1;
	public static final int THIRD_APP = 2;
	public static final int SDCARD_APP = 3;

	private Button mBtAllApp;
	private Button mBtSystemApp;
	private Button mBt3rdApp;
	private Button mBtSdcardApp;
	private ListView mListView;
	private PackageManager pm;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_application, container,
				false);
		mListView = (ListView) view.findViewById(R.id.listView_pm);
		mBtAllApp = (Button) view.findViewById(R.id.bt_show_all_app);
		mBtSystemApp = (Button) view.findViewById(R.id.bt_show_system_app);
		mBt3rdApp = (Button) view.findViewById(R.id.bt_show_3rd_app);
		mBtSdcardApp = (Button) view.findViewById(R.id.bt_show_sdcard_app);
		mBtSystemApp.setOnClickListener(this);
		mBtAllApp.setOnClickListener(this);
		mBt3rdApp.setOnClickListener(this);
		mBtSdcardApp.setOnClickListener(this);
		return view;
	}

	private List<PMAppInfo> getAppInfo(int flag) {
		// 获取PackageManager对象
		pm = this.getActivity().getPackageManager();
		// 获取应用信息
		List<ApplicationInfo> listAppcations = pm
				.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		List<PMAppInfo> appInfos = new ArrayList<PMAppInfo>();
		// 判断应用类型
		switch (flag) {
		case ALL_APP:
			appInfos.clear();
			for (ApplicationInfo app : listAppcations) {
				appInfos.add(makeAppInfo(app));
			}
			break;
		case SYSTEM_APP:
			appInfos.clear();
			for (ApplicationInfo app : listAppcations) {
				if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
					appInfos.add(makeAppInfo(app));
				}
			}
			break;
		case THIRD_APP:
			appInfos.clear();
			for (ApplicationInfo app : listAppcations) {
				if ((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
					appInfos.add(makeAppInfo(app));
				} else if ((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
					appInfos.add(makeAppInfo(app));
				}
			}
			break;
		case SDCARD_APP:
			appInfos.clear();
			for (ApplicationInfo app : listAppcations) {
				if ((app.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
					appInfos.add(makeAppInfo(app));
				}
			}
			break;
		default:
			return null;
		}
		return appInfos;
	}

	private PMAppInfo makeAppInfo(ApplicationInfo app) {
		PMAppInfo appInfo = new PMAppInfo();
		appInfo.setAppLabel((String) app.loadLabel(pm));
		appInfo.setAppIcon(app.loadIcon(pm));
		appInfo.setPkgName(app.packageName);
		return appInfo;
	}

	public void setListData(int flag) {
		PMAdapter adapter = new PMAdapter(getContext(), getAppInfo(flag));
		mListView.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_show_all_app:
			setListData(ALL_APP);
			break;
		case R.id.bt_show_system_app:
			setListData(SYSTEM_APP);
			break;
		case R.id.bt_show_3rd_app:
			setListData(THIRD_APP);
			break;
		case R.id.bt_show_sdcard_app:
			setListData(SDCARD_APP);
			break;

		default:
			break;
		}
	}
}
