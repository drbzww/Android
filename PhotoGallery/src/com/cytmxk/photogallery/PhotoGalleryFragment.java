package com.cytmxk.photogallery;

import java.io.IOException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class PhotoGalleryFragment extends Fragment {

	public static final String TAG = "PhotoGalleryFragment";
	private GridView mGridView = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		
		new FetchItemsTask().execute();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_photo_gallery, container, false);
		mGridView = (GridView) view.findViewById(R.id.gv_fragment_photo_gallery);
		return view;
	}
	
	private class FetchItemsTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				String url = new FlickrFetchr().getUrl("http://www.baidu.com");
				Log.i(TAG,"Fetched contents of URL: " + url );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.i(TAG,"Failed to fetch URL: " + e );
			}
			return null;
		}

		
	}
}
