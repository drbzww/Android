package com.cytmxk.testservice.test.foreground;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cytmxk.testservice.R;

public class ForeGroundFragment extends Fragment implements
		View.OnClickListener {

	private Button mBtStartForeground = null;
	private Button mBtStopForeground = null;
	private Button mBtBindForeground = null;
	private Button mBtUnBindForeground = null;

	private ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub

		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_foreground, container,
				false);
		mBtStartForeground = (Button) view
				.findViewById(R.id.bt_start_foreground_service);
		mBtStopForeground = (Button) view
				.findViewById(R.id.bt_stop_foreground_service);
		mBtBindForeground = (Button) view
				.findViewById(R.id.bt_bind_foreground_service);
		mBtUnBindForeground = (Button) view
				.findViewById(R.id.bt_unbind_foreground_service);
		mBtStartForeground.setOnClickListener(this);
		mBtStopForeground.setOnClickListener(this);
		mBtBindForeground.setOnClickListener(this);
		mBtUnBindForeground.setOnClickListener(this);
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
		case R.id.bt_bind_foreground_service:
			getActivity().bindService(
					new Intent(getActivity(), FroegroundService.class),
					mServiceConnection, Context.BIND_AUTO_CREATE);
			break;
		case R.id.bt_unbind_foreground_service:
			getActivity().unbindService(mServiceConnection);
			break;

		default:
			break;
		}
	}

}
