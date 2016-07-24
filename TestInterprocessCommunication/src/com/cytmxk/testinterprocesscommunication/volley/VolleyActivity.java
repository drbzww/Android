package com.cytmxk.testinterprocesscommunication.volley;

import android.support.v4.app.Fragment;

import com.cytmxk.testinterprocesscommunication.SingleFragmentActivity;

public class VolleyActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new VolleyFragment();
	}

}
