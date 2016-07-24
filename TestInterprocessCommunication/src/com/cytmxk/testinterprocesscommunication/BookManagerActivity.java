package com.cytmxk.testinterprocesscommunication;

import android.support.v4.app.Fragment;

public class BookManagerActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new BookManagerFragment();
	}

}
