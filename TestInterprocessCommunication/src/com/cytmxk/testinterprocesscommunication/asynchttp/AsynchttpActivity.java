package com.cytmxk.testinterprocesscommunication.asynchttp;

import android.support.v4.app.Fragment;

import com.cytmxk.testinterprocesscommunication.SingleFragmentActivity;

public class AsynchttpActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new AsynchttpFragment();
	}

}
