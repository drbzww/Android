package com.cytmxk.customview.test.scroll;

import android.support.v4.app.Fragment;
import com.cytmxk.customview.SingleFragmentActivity;

public class ScrollActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		int scrollWay = getIntent().getExtras().getInt("Scroll Way", -1);
		android.util.Log.i("ScrollActivity", "scrollWay = " + scrollWay);
		return ScrollFragment.newInstance(scrollWay);
	}

}
