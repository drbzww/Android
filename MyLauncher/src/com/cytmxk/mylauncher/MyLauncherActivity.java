package com.cytmxk.mylauncher;

import android.support.v4.app.Fragment;

public class MyLauncherActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new MyLauncherFragment();
	}

}
