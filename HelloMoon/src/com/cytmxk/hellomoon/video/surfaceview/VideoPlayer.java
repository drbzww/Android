package com.cytmxk.hellomoon.video.surfaceview;

import com.cytmxk.hellomoon.R;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;

public class VideoPlayer {

	private MediaPlayer mMediaPlayer = null;
	private int mPosition = 0;
	private SurfaceHolder mHolder = null;
	
	public void play(Context context, SurfaceHolder holder) {
		
		this.mHolder = holder;
		
		if (null == mMediaPlayer) {
			mMediaPlayer = MediaPlayer.create(context, R.raw.test_play_video);
		}
		mMediaPlayer.setDisplay(mHolder);
		mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				stop();
			}
		});
		android.util.Log.i("chenyang","play mPosition = " + mPosition);
		mMediaPlayer.seekTo(mPosition);
		mMediaPlayer.start();
	}
	
	public void pause() {
		if (null != mMediaPlayer) {
			if (mMediaPlayer.isPlaying()) {
				mPosition = mMediaPlayer.getCurrentPosition();
				android.util.Log.i("chenyang","pause mPosition = " + mPosition);
				mMediaPlayer.pause();
			}
		}
	}
	
	public void stop() {
		if (null != mMediaPlayer) {
			mMediaPlayer.release();
			mMediaPlayer = null;
			mPosition = 0;
			android.util.Log.i("chenyang","stop mPosition = " + mPosition);
		}
	}
}
