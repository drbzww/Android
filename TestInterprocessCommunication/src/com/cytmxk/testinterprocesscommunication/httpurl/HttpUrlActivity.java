package com.cytmxk.testinterprocesscommunication.httpurl;

import android.support.v4.app.Fragment;

import com.cytmxk.testinterprocesscommunication.SingleFragmentActivity;

public class HttpUrlActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new HttpUrlFragment();
	}

}
