package com.cytmxk.testanimation.property;

import com.cytmxk.testanimation.R;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class PropertyAnimationFragment extends Fragment implements
		View.OnClickListener {

	private Button mBtChangeAlpha = null;
	private Button mBtChangeRotationX = null;
	private Button mBtChangeScaleX = null;
	private Button mBtChangeTranslationY = null;
	private Button mBtChangeBackgroundcolor = null;
	private Button mBtChangeCombination = null;
	private Button mBtChangeVisible = null;
	private ImageView mBtHiddenView = null;
	private ImageView mImageView = null;
	
	private int mHiddenViewHeight = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_property_animation,
				container, false);
		mBtChangeTranslationY = (Button) view
				.findViewById(R.id.change_property_tranlationY);
		mBtChangeBackgroundcolor = (Button) view
				.findViewById(R.id.change_property_background_color);
		mBtChangeCombination = (Button) view
				.findViewById(R.id.change_combination_property);
		mBtChangeAlpha = (Button) view.findViewById(R.id.change_property_alpha);
		mBtChangeRotationX = (Button) view.findViewById(R.id.change_property_rotationX);
		mBtChangeScaleX = (Button) view.findViewById(R.id.change_property_scaleX);
		mBtChangeVisible = (Button) view.findViewById(R.id.change_visible);
		mBtHiddenView = (ImageView) view.findViewById(R.id.hidden_view);
		mImageView = (ImageView) view.findViewById(R.id.image);

		mBtChangeTranslationY.setOnClickListener(this);
		mBtChangeBackgroundcolor.setOnClickListener(this);
		mBtChangeCombination.setOnClickListener(this);
		mBtChangeAlpha.setOnClickListener(this);
		mBtChangeRotationX.setOnClickListener(this);
		mBtChangeScaleX.setOnClickListener(this);
		mBtChangeVisible.setOnClickListener(this);
		mImageView.setOnClickListener(this);
		
		mHiddenViewHeight = (int)(getResources().getDisplayMetrics().density*100 + 0.5);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.change_property_alpha:
			ObjectAnimator.ofFloat(mImageView, "alpha", 1, 0, 1)
					.setDuration(3000).start();
			break;
		case R.id.change_property_rotationX:
			mImageView.setPivotX(0);
			mImageView.setPivotY(50);
			ObjectAnimator.ofFloat(mImageView, "rotationX", 0, 180, 0)
					.setDuration(3000).start();
			break;
		case R.id.change_property_scaleX:
			mImageView.setPivotX(0);
			mImageView.setPivotY(50);
			ObjectAnimator.ofFloat(mImageView, "scaleX", 1, 1.5f, 1)
					.setDuration(3000).start();
			break;
		case R.id.change_property_tranlationY:
			ObjectAnimator.ofFloat(mImageView, "translationY", -200, 200, 0)
					.setDuration(3000).start();
			break;
		case R.id.change_property_background_color:
			ObjectAnimator animator = ObjectAnimator.ofInt(mImageView,
					"backgroundColor", 0x00000000, 0xFFFF0000, 0xFF0000FF)
					.setDuration(3000);
			animator.setEvaluator(new ArgbEvaluator());
			animator.setRepeatCount(ValueAnimator.INFINITE);
			animator.setRepeatMode(ValueAnimator.REVERSE);
			animator.start();
			break;
		case R.id.change_combination_property:
			
			//利用AnimatorSet实现同时播放多个动画效果
			AnimatorSet animatorSet = new AnimatorSet();
			animatorSet.playTogether(
					ObjectAnimator.ofFloat(mImageView, "rotationX", 0, 360),
					ObjectAnimator.ofFloat(mImageView, "rotationY", 0, 180),
					ObjectAnimator.ofFloat(mImageView, "rotation", 0, -90),
					ObjectAnimator.ofFloat(mImageView, "translationX", 0, 90),
					ObjectAnimator.ofFloat(mImageView, "translationY", 0, 90),
					ObjectAnimator.ofFloat(mImageView, "scaleX", 1, 1.5f),
					ObjectAnimator.ofFloat(mImageView, "scaleY", 1, 0.5f),
					ObjectAnimator.ofFloat(mImageView, "alpha", 1, 0.25f, 1));
			
			//利用AnimatorSet实现顺序播放多个动画效果
			final AnimatorSet animatorSet1 = new AnimatorSet();
			animatorSet1.playSequentially(
					ObjectAnimator.ofFloat(mImageView, "rotationX", 0, 360),
					ObjectAnimator.ofFloat(mImageView, "rotationY", 0, 180),
					ObjectAnimator.ofFloat(mImageView, "rotation", 0, -90),
					ObjectAnimator.ofFloat(mImageView, "translationX", 0, 90),
					ObjectAnimator.ofFloat(mImageView, "translationY", 0, 90),
					ObjectAnimator.ofFloat(mImageView, "scaleX", 1, 1.5f),
					ObjectAnimator.ofFloat(mImageView, "scaleY", 1, 0.5f),
					ObjectAnimator.ofFloat(mImageView, "alpha", 1, 0.25f, 1));
			
			//利用AnimatorSet实现顺序播放多个动画效果
			final AnimatorSet animatorSet2 = new AnimatorSet();
			animatorSet2
					.play(ObjectAnimator.ofFloat(mImageView, "rotationX", 0,
							360))
					.with(ObjectAnimator.ofFloat(mImageView, "rotationY", 0,
							180))
					.before(ObjectAnimator.ofFloat(mImageView, "rotation", 0,
							-90))
					.after(ObjectAnimator.ofFloat(mImageView, "alpha", 1,
							0.25f, 1));
			
			animatorSet.addListener(new Animator.AnimatorListener() {
				
				@Override
				public void onAnimationStart(Animator animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationRepeat(Animator animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationEnd(Animator animation) {
					// TODO Auto-generated method stub
					animatorSet1.setDuration(1000).start();
				}
				
				@Override
				public void onAnimationCancel(Animator animation) {
					// TODO Auto-generated method stub
					
				}
			});
			
			animatorSet1.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					// TODO Auto-generated method stub
					super.onAnimationEnd(animation);
					animatorSet2.setDuration(1000).start();
				}
			});
			
			animatorSet.setDuration(1000).start();

			//利用PropertyValuesHolder实现同时播放多个动画效果
			/*ObjectAnimator
					.ofPropertyValuesHolder(
							mImageView,
							PropertyValuesHolder.ofFloat("rotationX", 0, 360),
							PropertyValuesHolder.ofFloat("rotationY", 0, 180),
							PropertyValuesHolder.ofFloat("rotation", 0, -90),
							PropertyValuesHolder.ofFloat("translationX", 0, 90),
							PropertyValuesHolder.ofFloat("translationY", 0, 90),
							PropertyValuesHolder.ofFloat("scaleX", 1, 1.5f),
							PropertyValuesHolder.ofFloat("scaleY", 1, 0.5f),
							PropertyValuesHolder.ofFloat("alpha", 1, 0.25f, 1))
					.setDuration(1000).start();*/

			break;
		case R.id.change_visible:
			if (mBtHiddenView.getVisibility() == View.GONE) {
				animateOPen(mBtHiddenView);
			} else {
				animateClose(mBtHiddenView);
			}
			
			break;
		case R.id.image:
			Toast.makeText(getActivity(), R.string.click_image_view,
					Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}
	}
	
	private void animateOPen(final View view) {
		view.setVisibility(View.VISIBLE);
		ValueAnimator valueAnimator = createDropAnimator(view, 0, mHiddenViewHeight);
		valueAnimator.setInterpolator(new AccelerateInterpolator());
		valueAnimator.setDuration(1000).start();
	}
	
	private void animateClose(final View view) {
		ValueAnimator valueAnimator = createDropAnimator(view, mHiddenViewHeight, 0);
		valueAnimator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				super.onAnimationEnd(animation);
				view.setVisibility(View.GONE);
			}
		});
		valueAnimator.setInterpolator(new DecelerateInterpolator());
		valueAnimator.setDuration(1000).start();
	}
	
	private ValueAnimator createDropAnimator(final View view, int start, int end) {
		ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
		valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				LayoutParams params = view.getLayoutParams();
				params.height = (int) animation.getAnimatedValue();
				android.util.Log.i("chenyang", "createDropAnimator params.height = " + params.height);
				view.setLayoutParams(params);
			}
		});
		return valueAnimator;
	}
}
