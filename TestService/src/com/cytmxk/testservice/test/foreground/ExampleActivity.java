package com.cytmxk.testservice.test.foreground;

import com.cytmxk.testservice.SingleFragmentActivity;
import android.support.v4.app.Fragment;

public class ExampleActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new ExampleFragment();
	}

}
