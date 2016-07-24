package com.cytmxk.testdialog.alertdialog.twostage;

import com.cytmxk.testdialog.R;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public abstract class SingleDialogFragmentActivity extends FragmentActivity {

	public abstract DialogFragment createFragment();

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		setContentView(R.layout.activity_fragment);
		FragmentManager fm = getSupportFragmentManager();
		DialogFragment fragment = (DialogFragment) fm.findFragmentById(R.id.fragment_container);
		if (null == fragment) {
			fragment = createFragment();
			fragment.show(fm, "SingleFragmentActivity");
		}
	}
}
