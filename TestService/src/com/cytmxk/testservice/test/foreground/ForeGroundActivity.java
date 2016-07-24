package com.cytmxk.testservice.test.foreground;

import android.support.v4.app.Fragment;

import com.cytmxk.testservice.SingleFragmentActivity;

public class ForeGroundActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new ForeGroundFragment();
	}

}
