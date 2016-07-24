package com.cytmxk.criminalintent;

import android.support.v4.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity {

	@Override
	public Fragment createFragment() {
		// TODO Auto-generated method stub
		return new CrimeListFragment();
	}

}
