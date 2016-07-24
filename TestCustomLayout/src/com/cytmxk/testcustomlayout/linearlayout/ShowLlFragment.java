package com.cytmxk.testcustomlayout.linearlayout;

import com.cytmxk.testcustomlayout.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class ShowLlFragment extends Fragment implements OnClickListener {

	SagerealLinearLayoutButton mSagerealLinearLayoutButton = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_show_ll, container,
				false);
		mSagerealLinearLayoutButton = (SagerealLinearLayoutButton) view
				.findViewById(R.id.sagereal_linearlayout_btn);
		mSagerealLinearLayoutButton.addButtons(new int[] { R.drawable.fab_grey,
				R.drawable.fab_ic_dial }, new int[] { R.id.bt_fab_grey,
				R.id.bt_fab_ic_dial });
		ImageButton ivFabGrey = (ImageButton) mSagerealLinearLayoutButton
				.findViewById(R.id.bt_fab_grey);
		ivFabGrey.setOnClickListener(this);
		ImageButton ivFabDial = (ImageButton) mSagerealLinearLayoutButton
				.findViewById(R.id.bt_fab_ic_dial);
		ivFabDial.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bt_fab_grey:
            android.util.Log.i("chenyang","R.id.bt_fab_grey");
			break;
		case R.id.bt_fab_ic_dial:
			android.util.Log.i("chenyang","R.id.bt_fab_ic_dial");
			break;

		default:
			break;
		}
	}
}
