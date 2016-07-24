package com.cytmxk.testinterprocesscommunication;

import android.support.v4.app.Fragment;

public class TwoActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return MainFragment.newInstance(R.layout.fragment_two);
	}

}
