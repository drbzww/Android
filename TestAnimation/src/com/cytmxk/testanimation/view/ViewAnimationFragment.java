package com.cytmxk.testanimation.view;

import com.cytmxk.testanimation.R;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ViewAnimationFragment extends Fragment implements
		View.OnClickListener {

	private ImageView image;
	private Button scale;
	private Button rotate;
	private Button translate;
	private Button alpha;
	private Button combinationBt1;
	private Button combinationBt2;
	private Button flash;
	private Button shake;
	private Button change;
	private Button layout;
	private Button frame;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_view_animation,
				container, false);
		image = (ImageView) view.findViewById(R.id.image);
		scale = (Button) view.findViewById(R.id.scale);
		rotate = (Button) view.findViewById(R.id.rotate);
		translate = (Button) view.findViewById(R.id.translate);
		alpha = (Button) view.findViewById(R.id.alpha);
		combinationBt1 = (Button) view.findViewById(R.id.combination_bt1);
		combinationBt2 = (Button) view.findViewById(R.id.combination_bt2);
		flash = (Button) view.findViewById(R.id.flash);
		shake = (Button) view.findViewById(R.id.shake);
		change = (Button) view.findViewById(R.id.change);
		layout = (Button) view.findViewById(R.id.layout);
		frame = (Button) view.findViewById(R.id.frame);
		scale.setOnClickListener(this);
		rotate.setOnClickListener(this);
		translate.setOnClickListener(this);
		alpha.setOnClickListener(this);
		combinationBt1.setOnClickListener(this);
		combinationBt2.setOnClickListener(this);
		flash.setOnClickListener(this);
		shake.setOnClickListener(this);
		change.setOnClickListener(this);
		layout.setOnClickListener(this);
		frame.setOnClickListener(this);
		image.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Animation loadAnimation;
		switch (v.getId()) {
		case R.id.image: {
			Toast.makeText(getActivity(), R.string.click_image_view,
					Toast.LENGTH_LONG).show();
			break;
		}
		
		case R.id.alpha: {
			loadAnimation = AnimationUtils.loadAnimation(getActivity(),
					R.anim.base_animation_alpha);
			image.startAnimation(loadAnimation);
			break;
		}

		case R.id.scale: {
			loadAnimation = AnimationUtils.loadAnimation(getActivity(),
					R.anim.base_animation_scale);
			image.startAnimation(loadAnimation);
			break;
		}

		case R.id.rotate: {
			loadAnimation = AnimationUtils.loadAnimation(getActivity(),
					R.anim.base_anmation_rotate);
			image.startAnimation(loadAnimation);
			break;
		}

		case R.id.translate: {

			loadAnimation = AnimationUtils.loadAnimation(getActivity(),
					R.anim.base_aniamtion_translate);
			image.startAnimation(loadAnimation);
			break;
		}

		case R.id.combination_bt1: {
			//通过监听前一个动画的结束事件，来组合播放多个动画
			/*loadAnimation = AnimationUtils.loadAnimation(getActivity(),
					R.anim.base_aniamtion_translate);
			image.startAnimation(loadAnimation);
			final Animation loadAnimation2 = AnimationUtils.loadAnimation(
					getActivity(), R.anim.base_anmation_rotate);
			loadAnimation.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationEnd(Animation arg0) {
					// TODO Auto-generated method stub
					image.startAnimation(loadAnimation2);
				}
			});*/
			
			//通过xml分别定义每个动画（res/anim/filename）, 然后通过代码创建执行动画
			/*AnimationSet animationSet = new AnimationSet(true);
			animationSet.addAnimation(AnimationUtils.loadAnimation(
					getActivity(), R.anim.base_aniamtion_translate));
			animationSet.addAnimation(AnimationUtils.loadAnimation(
					getActivity(), R.anim.base_anmation_rotate));
			animationSet.setFillAfter(true);
			image.startAnimation(animationSet);*/
			
			//代码定义、创建、执行动画
	        /*AnimationSet animationSet = new AnimationSet(true);
	        TranslateAnimation readyAnim = new TranslateAnimation(
	        TranslateAnimation.RELATIVE_TO_SELF, 1f,
	        TranslateAnimation.RELATIVE_TO_PARENT, 0.2f,
	        TranslateAnimation.RELATIVE_TO_SELF, 0f,
	        TranslateAnimation.RELATIVE_TO_SELF, 0f);
	        readyAnim.setDuration(500);
	        animationSet.addAnimation(readyAnim);

	        TranslateAnimation outAnim = new TranslateAnimation(
	        TranslateAnimation.RELATIVE_TO_SELF, -1f,
	        TranslateAnimation.RELATIVE_TO_PARENT, -0.2f,
	        TranslateAnimation.RELATIVE_TO_SELF, 0,
	        TranslateAnimation.RELATIVE_TO_SELF, 0);
	        outAnim.setDuration(500);
	        outAnim.setStartOffset(500);
	        animationSet.addAnimation(outAnim);

	        TranslateAnimation inAnim = new TranslateAnimation(
	        TranslateAnimation.RELATIVE_TO_SELF, 0.5f,
	        TranslateAnimation.RELATIVE_TO_PARENT, 0.2f,
	        TranslateAnimation.RELATIVE_TO_SELF, 0,
	        TranslateAnimation.RELATIVE_TO_SELF, 0);
	        inAnim.setDuration(500);
	        inAnim.setStartOffset(1000);
	        animationSet.addAnimation(inAnim);
			animationSet.setFillAfter(true);
			image.startAnimation(animationSet);*/
			
			//代码定义、创建、执行动画
			/*AnimationSet animationSet = new AnimationSet(true);
			AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.8f, 0.5f);
			alphaAnimation1.setDuration(2000);
			animationSet.addAnimation(alphaAnimation1);
			AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, 0.5f);
			alphaAnimation2.setDuration(2000);
			alphaAnimation2.setStartOffset(4000);
			animationSet.addAnimation(alphaAnimation2);
			animationSet.setFillAfter(true);
			image.startAnimation(animationSet);*/
			
			// 代码定义、创建、执行动画
			AnimationSet animationSet = new AnimationSet(true);
			RotateAnimation rotateAnimation1 = new RotateAnimation(0, 180,
					RotateAnimation.RELATIVE_TO_SELF, 0.5f,
					RotateAnimation.RELATIVE_TO_SELF, 0.5f);
			rotateAnimation1.setDuration(2000);
			animationSet.addAnimation(rotateAnimation1);
			RotateAnimation rotateAnimation2 = new RotateAnimation(0, 180,
					RotateAnimation.RELATIVE_TO_SELF, 0f,
					RotateAnimation.RELATIVE_TO_SELF, 1f);
			rotateAnimation2.setDuration(2000);
			rotateAnimation2.setStartOffset(2000);
			animationSet.addAnimation(rotateAnimation2);
			animationSet.setFillAfter(true);
			image.startAnimation(animationSet);
			
			// 代码定义、创建、执行动画
			/*AnimationSet animationSet = new AnimationSet(true);
			ScaleAnimation scaleAnimation1 = new ScaleAnimation(0, 1, 0, 1,
					ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
					ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
			scaleAnimation1.setDuration(2000);
			animationSet.addAnimation(scaleAnimation1);
			ScaleAnimation scaleAnimation2 = new ScaleAnimation(1, 0, 1, 0,
					ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
					ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
			scaleAnimation2.setDuration(2000);
			scaleAnimation2.setStartOffset(2000);
			animationSet.addAnimation(scaleAnimation2);
			animationSet.setFillAfter(true);
			image.startAnimation(animationSet);*/
			
			break;
		}

		case R.id.combination_bt2: {
			//通过一个xml文件定义多个动画（res/anim/filename）, 然后通过代码创建执行动画
			loadAnimation = AnimationUtils.loadAnimation(getActivity(),
					R.anim.combination_anim);
			image.startAnimation(loadAnimation);
			break;
		}

		case R.id.shake: {
			TranslateAnimation translate = new TranslateAnimation(-50, 50, 0, 0);
			translate.setDuration(1000);
			translate.setRepeatCount(Animation.INFINITE);
			translate.setRepeatMode(Animation.REVERSE);
			image.startAnimation(translate);

			break;
		}

		case R.id.flash: {

			AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
			alphaAnimation.setDuration(100);
			alphaAnimation.setRepeatCount(10);
			// 倒序重复REVERSE 正序重复RESTART
			alphaAnimation.setRepeatMode(Animation.REVERSE);
			image.startAnimation(alphaAnimation);

			break;
		}

		case R.id.change: {
			Intent intent = new Intent(getActivity(), ChangedActivity.class);
			startActivity(intent);
			getActivity().overridePendingTransition(R.anim.zoom_in,
					R.anim.zoom_out);
			break;
		}

		case R.id.layout: {
			Intent intent = new Intent(getActivity(), LayoutActivity.class);
			startActivity(intent);
			break;
		}

		case R.id.frame: {
			
			//通过xml定义动画（res/anim/filename），然后通过代码创建执行动画
			/*image.setImageResource(R.drawable.anim_list);
			AnimationDrawable animationDrawable = (AnimationDrawable) image.getDrawable();
			animationDrawable.start();*/
			//animationDrawable.stop(); //停止动画
			
			//代码定义、创建、执行动画
			AnimationDrawable animationDrawable = new AnimationDrawable();
			animationDrawable.addFrame(getResources().getDrawable(R.drawable.first_pic), 500);
			animationDrawable.addFrame(getResources().getDrawable(R.drawable.second_pic), 500);
			animationDrawable.addFrame(getResources().getDrawable(R.drawable.third_pic), 500);
			animationDrawable.addFrame(getResources().getDrawable(R.drawable.fourth_pic), 500);
			animationDrawable.addFrame(getResources().getDrawable(R.drawable.fifth_pic), 500);
			animationDrawable.addFrame(getResources().getDrawable(R.drawable.sixth_pic), 500);
			animationDrawable.setOneShot(true);
			image.setImageDrawable(animationDrawable);
			animationDrawable.start();
			
			break;

		}

		}
	}
}
