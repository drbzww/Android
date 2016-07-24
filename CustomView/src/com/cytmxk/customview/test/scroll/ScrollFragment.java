package com.cytmxk.customview.test.scroll;

import com.cytmxk.customview.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScrollFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = null;
		int scrollWay = getArguments().getInt("Scroll Way");
		android.util.Log.i("ScrollActivity", "scrollWayqq = " + scrollWay);
		switch (scrollWay) {
		case 0:
			view = inflater.inflate(R.layout.fragment_layout_scroll, container, false);
			break;

		default:
			break;
		}
		return view;
	}
	
	public static ScrollFragment newInstance(int scrollWay) {
		android.util.Log.i("ScrollActivity", "scrollWay ll= " + scrollWay);
		ScrollFragment scrollFragment = new ScrollFragment();
		Bundle args = new Bundle();
		args.putInt("Scroll Way", scrollWay);
		scrollFragment.setArguments(args);
		return scrollFragment;
	}
}
