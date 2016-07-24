package com.cytmxk.hellomoon.video.videoview;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoPlayer {

	private MediaController mMediaController = null;
	private int mPosition = 0;
	private VideoView mVideoView = null;

	public void play(Context context, VideoView videoView) {

		this.mVideoView = videoView;

		if (null == mMediaController) {
			mMediaController = new MediaController(context);
		}
		mVideoView
				.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

					@Override
					public void onCompletion(MediaPlayer mp) {
						// TODO Auto-generated method stub
						mPosition = 0;
					}
				});
		mVideoView.setVideoURI(Uri.parse("android.resource://" + "com.cytmxk.hellomoon/raw/test_play_video"));
		mVideoView.setMediaController(mMediaController);
		mVideoView.seekTo(mPosition);
		mVideoView.start();

	}

	public void pause() {
		if (null != mVideoView) {
			if (mVideoView.isPlaying()) {
				mPosition = mVideoView.getCurrentPosition();
				android.util.Log
						.i("chenyang", "pause mPosition = " + mPosition);
				mVideoView.pause();
			}
		}
	}

	public void stop() {
		mPosition = 0;
		android.util.Log.i("chenyang", "stop mPosition = " + mPosition);
	}
}
