package com.cytmxk.hellomoon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HelloMoonFragment extends Fragment {

	private Button mBtPlay = null;
	private Button mBtPause = null;
	private Button mBtStop = null;
	private AudioPlayer mAudioPlayer = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setRetainInstance(true);
		mAudioPlayer = new AudioPlayer();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.fragment_hello_moon, container, false);
		mBtPlay = (Button) view.findViewById(R.id.bt_hellomoon_play);
		mBtPause = (Button) view.findViewById(R.id.bt_hellomoon_pause);
		mBtStop = (Button) view.findViewById(R.id.bt_hellomoon_stop);
		
		mBtPlay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mAudioPlayer.play(getActivity());
			}
		});
		mBtPause.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mAudioPlayer.pause();
			}
		});
		mBtStop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mAudioPlayer.stop();
			}
		});
		return view;
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mAudioPlayer.stop();
	}
}
