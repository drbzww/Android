package com.cytmxk.testmessageprocessing.asynctask;

import android.support.v4.app.Fragment;

import com.cytmxk.testmessageprocessing.SingleFragmentActivity;

public class AsyncTaskActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new AysncTaskFragment();
	}

}
