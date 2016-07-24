package com.cytmxk.hellmoon.picture.surfaceview;

import java.util.ArrayList;
import java.util.List;
import com.cytmxk.hellomoon.R;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlayPictureFragment extends Fragment {

	private ImageSurfaceView mImageSurfaceView = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		List<Integer> pictureResIdList = new ArrayList<Integer>();
		TypedArray bootImageIdArray = getResources().obtainTypedArray(R.array.boot_image_id_array);
		for (int i = 0; i < bootImageIdArray.length(); i++) {
			pictureResIdList.add(bootImageIdArray.getResourceId(i, 0));
		}
		bootImageIdArray.recycle();
		mImageSurfaceView = new ImageSurfaceView(getActivity(), pictureResIdList, 16);
		return mImageSurfaceView;
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
