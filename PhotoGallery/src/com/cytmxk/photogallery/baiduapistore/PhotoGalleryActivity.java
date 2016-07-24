package com.cytmxk.photogallery.baiduapistore;

import com.cytmxk.photogallery.R;

import android.app.SearchManager;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;

public class PhotoGalleryActivity extends SingleFragmentActivity {

	private static final String TAG = "PhotoGalleryActivity";

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new PhotoGalleryFragment();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		if (intent.getAction().equals(Intent.ACTION_SEARCH)) {
			String quary = intent.getExtras().getString(SearchManager.QUERY);
			android.util.Log.i(TAG, "Received a new search quary : " + quary);
			PreferenceManager.getDefaultSharedPreferences(this).edit()
					.putString(BaiduFetchr.PREF_SEARCH_QUARY, quary).commit();
		}
		PhotoGalleryFragment fragment = (PhotoGalleryFragment) getSupportFragmentManager()
				.findFragmentById(R.id.fragmentContainer);
		fragment.updateItems();
	}

}
