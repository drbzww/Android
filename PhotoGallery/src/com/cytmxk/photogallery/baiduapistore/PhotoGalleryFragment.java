package com.cytmxk.photogallery.baiduapistore;

import java.util.ArrayList;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.cytmxk.photogallery.R;
import com.cytmxk.photogallery.entry.GalleryItem;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;

public class PhotoGalleryFragment extends Fragment {

	public static final String TAG = "PhotoGalleryFragment";
	private GridView mGridView = null;
	private ArrayList<GalleryItem> mItems = new ArrayList<GalleryItem>();
	private ThumbnailDownloader<ImageView> mThumbnailDownloader = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		setHasOptionsMenu(true);

		executeApiStoreSDK(this.mItems);
		mThumbnailDownloader = new ThumbnailDownloader<ImageView>(getActivity(), new Handler());
		mThumbnailDownloader.setListener(new ThumbnailDownloader.Listener<ImageView>() {

			@Override
			public void onThumbnailDownloaded(ImageView imageView, Bitmap bitmap) {
				// TODO Auto-generated method stub
				if (isVisible()) {
					imageView.setImageBitmap(bitmap);
				}
			}
		});
		mThumbnailDownloader.start();
		mThumbnailDownloader.getLooper();
		LogUtils.i(TAG,"Background thread started");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_photo_gallery,
				container, false);
		mGridView = (GridView) view
				.findViewById(R.id.gv_fragment_photo_gallery);
		setupAdapter();
		return view;
	}

	private void setupAdapter() {
		if (null == getActivity() || null == mGridView) {
			return;
		}
		if (null != mItems) {
			mGridView.setAdapter(new GalleryItemAdapter(mItems));
		} else {
			mGridView.setAdapter(null);
		}
	}

	public void executeApiStoreSDK(final ArrayList<GalleryItem> mItems) {
		Parameters para = new Parameters();
		Activity activity = getActivity();
		if (null == activity) {
			return;
		}
		final String searchQuary = PreferenceManager.getDefaultSharedPreferences(activity).getString(
				BaiduFetchr.PREF_SEARCH_QUARY, null);
		LogUtils.i("sdkdemo", "searchQuary = " + searchQuary);
		String endpoint = null;
		if (searchQuary == null) {
			para.put("id", 0);
			para.put("rows", 30);
			para.put("classify", 0);
			endpoint = BaiduFetchr.ENDPOINT_SHOW;
		} else {
			para.put("word", searchQuary);
			para.put("pn", 0);
			para.put("rn", 30);
			para.put("ie", "utf-8");
			endpoint = BaiduFetchr.ENDPOINT_SEARCH;
		}
		LogUtils.i("sdkdemo", "endpoint = " + endpoint);
		ApiStoreSDK.execute(endpoint, ApiStoreSDK.GET, para, new ApiCallBack() {
			@Override
			public void onSuccess(int status, String responseString) {
				LogUtils.i("sdkdemo", "onSuccess");
				LogUtils.i("sdkdemo", "onSuccess responseString = "
						+ responseString);
				mItems.clear();
				if (searchQuary == null) {
					new BaiduFetchr().parseItemsShow(mItems, responseString);
				} else {
					new BaiduFetchr().parseItemsSearch(mItems, responseString);
				}
				LogUtils.i("sdkdemo", mItems.toString());
				setupAdapter();
			}

			@Override
			public void onComplete() {
				LogUtils.i("sdkdemo", "onComplete");
			}

			@Override
			public void onError(int status, String responseString, Exception e) {
				LogUtils.i("sdkdemo", "onError, status: " + status);
				LogUtils.i("sdkdemo",
						"errMsg: " + (e == null ? "" : e.getMessage()));
				LogUtils.i("sdkdemo", "onError e = " + e);
			}

		});
		return;
	}

	private class GalleryItemAdapter extends ArrayAdapter<GalleryItem> {

		public GalleryItemAdapter(ArrayList<GalleryItem> items) {
			super(getActivity(), 0, items);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (null == convertView) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.gallery_item, parent, false);
			}
			ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_gallery_item);
			imageView.setImageResource(R.drawable.beauty4);
			GalleryItem galleryItem = getItem(position);
			imageView.setTag(galleryItem.getmUrl());
			mThumbnailDownloader.queneThumbnail(imageView, galleryItem.getmUrl());
			return convertView;
		}

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mThumbnailDownloader.diskLruCacheFlush();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mThumbnailDownloader.quit();
		LogUtils.i(TAG,"Background thread destoryed");
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		mThumbnailDownloader.cleanQueue();
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_photo_gallery, menu);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			MenuItem menuItem = menu.findItem(R.id.menu_item_search);
			SearchView searchView = (SearchView) menuItem.getActionView();
			SearchManager searchManager = (SearchManager) getActivity().getSystemService(Activity.SEARCH_SERVICE);
			SearchableInfo searchableInfo = searchManager.getSearchableInfo(getActivity().getComponentName());
			searchView.setSearchableInfo(searchableInfo);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.menu_item_search:
			getActivity().onSearchRequested();
            return true;
		case R.id.menu_item_clear_search:
			PreferenceManager.getDefaultSharedPreferences(getActivity()).edit()
			.putString(BaiduFetchr.PREF_SEARCH_QUARY, null).commit();
			updateItems();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void updateItems() {
		executeApiStoreSDK(this.mItems);
	}
}
