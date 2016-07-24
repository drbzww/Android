package com.cytmxk.testdialog.oncreateview;

import com.cytmxk.testdialog.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public abstract class SingleFragmentActivity extends FragmentActivity {

	public abstract Fragment createFragment();

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		setContentView(R.layout.activity_fragment);
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragment_container);
		if (null == fragment) {
			fragment = createFragment();
			fm.beginTransaction().add(R.id.fragment_container, fragment)
					.commit();
		}
	}
}
