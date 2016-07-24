package com.cytmxk.criminalintent;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class CrimeListActivity extends SingleFragmentActivity implements
		CrimeListFragment.Callbacks, CrimeFragment.Callbacks {

	@Override
	public Fragment createFragment() {
		// TODO Auto-generated method stub
		return new CrimeListFragment();
	}

	@Override
	protected int getLayoutResId() {
		// TODO Auto-generated method stub
		return R.layout.activity_masterdetail;
	}

	@Override
	public void onCrimeSelected(Crime crime) {
		// TODO Auto-generated method stub
		if (null == findViewById(R.id.detailFragmentContainer)) {
			Intent intent = new Intent(this, CrimePagerActivity.class);
			intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
			startActivity(intent);
		} else {
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction bt = fm.beginTransaction();
			Fragment oldDetail = fm
					.findFragmentById(R.id.detailFragmentContainer);
			CrimeFragment newDetail = CrimeFragment.newInstance(crime.getId());
			if (null != oldDetail) {
				bt.remove(oldDetail);
			}
			bt.add(R.id.detailFragmentContainer, newDetail);
			bt.commit();
		}
	}

	@Override
	public void onCrimeUpdated(Crime crime) {
		// TODO Auto-generated method stub
        FragmentManager fm = getSupportFragmentManager();
        CrimeListFragment listFragment = (CrimeListFragment) fm.findFragmentById(R.id.fragmentContainer);
        if (null != listFragment) {
        	listFragment.updateUI();
        }
	}

}
