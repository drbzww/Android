package com.cytmxk.testinterprocesscommunication;

import android.support.v4.app.Fragment;

public class OneActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return MainFragment.newInstance(R.layout.fragment_one);
	}

}
