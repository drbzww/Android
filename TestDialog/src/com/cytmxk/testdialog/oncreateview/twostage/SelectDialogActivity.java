package com.cytmxk.testdialog.oncreateview.twostage;

import android.support.v4.app.DialogFragment;

public class SelectDialogActivity extends SingleDialogFragmentActivity {

	@Override
	public DialogFragment createFragment() {
		// TODO Auto-generated method stub
		return new SelectDialogFragment();
	}

}
