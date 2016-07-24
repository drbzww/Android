package com.cytmxk.testinterprocesscommunication.sockettcp;

import android.support.v4.app.Fragment;

import com.cytmxk.testinterprocesscommunication.SingleFragmentActivity;

public class TCPClientActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new TCPClientFragment();
	}

}
