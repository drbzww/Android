package com.cytmxk.teststatusbar.immersive;

import android.support.v4.app.Fragment;
import com.cytmxk.teststatusbar.SingleFragmentActivity;

public class ImmersiveActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new ImmersiveFragment();
	}
}
