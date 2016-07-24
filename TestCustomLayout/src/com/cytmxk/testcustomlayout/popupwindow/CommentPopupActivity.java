package com.cytmxk.testcustomlayout.popupwindow;

import android.support.v4.app.Fragment;

import com.cytmxk.testcustomlayout.SingleFragmentActivity;

public class CommentPopupActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new CommentPopupFragment();
	}

}
