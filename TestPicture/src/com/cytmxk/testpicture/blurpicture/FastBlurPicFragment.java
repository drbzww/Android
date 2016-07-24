package com.cytmxk.testpicture.blurpicture;

import com.cytmxk.testpicture.PictureUtils;
import com.cytmxk.testpicture.R;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FastBlurPicFragment extends Fragment {

	private ImageView mIvShowPic = null;
	private TextView mShowElapsedTime = null;
	private AsyncTask<Bitmap,Integer,Bitmap> mAsyncTask = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_blur_pic, container, false);
		mIvShowPic = (ImageView) view.findViewById(R.id.iv_show_blur_pic);
		mShowElapsedTime = (TextView) view.findViewById(R.id.tv_elapsed_time);
		fastBlurPic();
		return view;
	}
	
	private void fastBlurPic() {
		mAsyncTask = new FastBlurPicTask();
		mAsyncTask.execute(BitmapFactory.decodeResource(getResources(), R.drawable.beauty5));
	}
	
	class FastBlurPicTask extends AsyncTask<Bitmap,Integer,Bitmap>{
		
		@Override
		protected Bitmap doInBackground(Bitmap... params) {
			// TODO Auto-generated method stub
			
			return PictureUtils.fastBlur(params[0]);
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			mIvShowPic.setImageBitmap(result);
			mShowElapsedTime.setText("耗时：" + PictureUtils.getFastElapsedTimeBlur() + "ms");
			PictureUtils.clearFastElapsedTimeBlur();
		}
	}
	
}
