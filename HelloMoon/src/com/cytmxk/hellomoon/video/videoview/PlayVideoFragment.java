package com.cytmxk.hellomoon.video.videoview;

import com.cytmxk.hellomoon.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

public class PlayVideoFragment extends Fragment {

	private Button mBtPlay = null;
	private Button mBtPause = null;
	private Button mBtStop = null;
	private VideoView mVideoView = null;
	private VideoPlayer mVideoPlayer = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_play_video_videoview, container, false);
		mBtPlay = (Button) view.findViewById(R.id.bt_hellomoon_play);
		mBtPause = (Button) view.findViewById(R.id.bt_hellomoon_pause);
		mBtStop = (Button) view.findViewById(R.id.bt_hellomoon_stop);
		mVideoView = (VideoView) view.findViewById(R.id.vv_play_video);
		
		mVideoPlayer = new VideoPlayer();
		mBtPlay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mVideoPlayer.play(getActivity(), mVideoView);
			}
		});
		mBtPause.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mVideoPlayer.pause();
			}
		});
		mBtStop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mVideoPlayer.stop();
			}
		});
		return view;
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mVideoPlayer.stop();
	}
}
