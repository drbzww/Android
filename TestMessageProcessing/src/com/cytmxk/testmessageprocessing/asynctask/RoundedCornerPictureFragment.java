package com.cytmxk.testmessageprocessing.asynctask;

import com.cytmxk.testmessageprocessing.R;
import com.cytmxk.testmessageprocessing.asynctask.AsyncTaskUtil.AsyncTaskListener;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class RoundedCornerPictureFragment extends Fragment {

	private ImageView mIvShowBeauty = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_rounded_corner, container,
				false);
		mIvShowBeauty = (ImageView) view.findViewById(R.id.show_beauty);
		AsyncTaskUtil.getRoundedCornerPicture(getActivity(), BitmapFactory
				.decodeResource(getResources(), R.drawable.beauty1),
				mAsyncTaskListener);
		return view;
	}

	private AsyncTaskListener mAsyncTaskListener = new AsyncTaskListener() {

		@Override
		public void onGetResultPicture(Bitmap bitmap) {
			// TODO Auto-generated method stub
			mIvShowBeauty.setImageBitmap(bitmap);
		}
	};
}
