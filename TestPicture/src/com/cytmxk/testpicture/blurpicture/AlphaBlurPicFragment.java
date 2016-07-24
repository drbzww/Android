package com.cytmxk.testpicture.blurpicture;

import com.cytmxk.testpicture.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class AlphaBlurPicFragment extends Fragment {

	private ImageView mBlurImage;
	private TextView mTextView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_alpha_blur_pic, container, false);
		mTextView = (TextView) view.findViewById(R.id.main_alpha_text);
		mTextView.setText("透明度为：" + 1.0);
		
		mBlurImage = (ImageView) view.findViewById(R.id.main_blur_bg);
		mBlurImage.setOnTouchListener(new OnTouchListener() {

			private float mLastY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					mLastY = event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					float y = event.getY();
					float alphaDelt = (y - mLastY) / 1000;
					float alpha = mBlurImage.getAlpha() + alphaDelt;
					if (alpha > 1.0) {
						alpha = 1.0f;
					} else if (alpha < 0.0) {
						alpha = 0.0f;
					}
					mTextView.setText("透明度为：" + String.valueOf(alpha));
					mBlurImage.setAlpha(alpha);
					break;
				case MotionEvent.ACTION_UP:
					break;
				}
				return true;
			}
		});
		return view;
	}
}
