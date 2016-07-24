package com.cytmxk.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {

	private MediaPlayer mMediaPlayer = null;
	private int mPosition = 0;
	
	public void play(Context context) {
		if (null == mMediaPlayer) {
			mMediaPlayer = MediaPlayer.create(context, R.raw.one_small_step);
		}
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
