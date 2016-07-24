package com.cytmxk.testservice.test.foreground;

import com.cytmxk.testservice.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ExampleFragment extends Fragment implements View.OnClickListener {

	private Button mBtStartForeground = null;
	private Button mBtStopForeground = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_example, container,
				false);
		mBtStartForeground = (Button) view
				.findViewById(R.id.bt_start_foreground_service);
		mBtStopForeground = (Button) view
				.findViewById(R.id.bt_stop_foreground_service);
		mBtStartForeground.setOnClickListener(this);
		mBtStopForeground.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_start_foreground_service:
			getActivity().startService(
					new Intent(getActivity(), FroegroundService.class));
			break;
		case R.id.bt_stop_foreground_service:
			getActivity().stopService(
					new Intent(getActivity(), FroegroundService.class));
			break;
			
		default:
			break;
		}
	}
}
