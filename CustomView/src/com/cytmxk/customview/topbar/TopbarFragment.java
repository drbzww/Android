package com.cytmxk.customview.topbar;

import com.cytmxk.customview.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class TopbarFragment extends Fragment {
	
	private TopBar mTopBar = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_topbar, container, false);
		mTopBar = (TopBar) view.findViewById(R.id.top_bar);
		mTopBar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
			
			@Override
			public void rigntClick() {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "right", Toast.LENGTH_LONG).show();
			}
			
			@Override
			public void leftClick() {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "left", Toast.LENGTH_LONG).show();
			}
		});
		return view;
	}
}
