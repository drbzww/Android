package com.cytmxk.criminalintent;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageFragment extends DialogFragment {

	private static final String EXTRA_IMAGE_PATH = "com.cytmxk.criminalintent.image_path";
	
	private ImageView mImageView = null;
	
	public static ImageFragment newInstance(String iamgePath) {
		ImageFragment fragment = new ImageFragment();
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_IMAGE_PATH, iamgePath);
		fragment.setArguments(args);
		fragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mImageView = new ImageView(getActivity());
		String imagePath = (String) getArguments().getSerializable(EXTRA_IMAGE_PATH);
		mImageView.setImageDrawable(PictureUtils.getScaledDrawable(getActivity(), imagePath));
		return mImageView;
	}
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		PictureUtils.cleanImageView(mImageView);
	}
}
