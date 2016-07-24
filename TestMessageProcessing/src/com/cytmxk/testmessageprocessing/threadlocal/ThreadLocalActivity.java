package com.cytmxk.testmessageprocessing.threadlocal;

import android.support.v4.app.Fragment;

import com.cytmxk.testmessageprocessing.SingleFragmentActivity;

public class ThreadLocalActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new ThreadLocalFragment();
	}

}
